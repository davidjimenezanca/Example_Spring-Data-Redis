/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/spring-context.xml")
@org.junit.FixMethodOrder(NAME_ASCENDING)
public class RedisSetOperationsTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOps;

    @Test
    public void saveSingleStringInSet() {

        cleanDb();
        try{
            final Long add = setOps.add("test", "Today is Mickey Mouse birthday");
            assertTrue(add != null && add > 0);
            setOps.getOperations().persist("test");
        } catch(Exception e){
            fail("Test -saveSingleStringInSet- fails");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println("error ->" + e.getMessage());
        }
    }

    @Test
    public void trackStringInSet() {

        try{
            assertTrue(setOps.isMember("test", "Today is Mickey Mouse birthday"));
        } catch(Exception e){
            fail("Test -trackStringInSet- fails");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println("error ->" + e.getMessage());
        }
        cleanDb();
    }

    private void cleanDb(){

        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return "OK";
        });
    }

}
