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
package camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

public class CNNLatestProcessor implements Processor {

    //@Resource(name="redisTemplate")
    //private SetOperations<String, String> setOps;
    //@Autowired
    //private RedisTemplate<String, String> template;

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        body = body.replaceAll(">\\s+<", "><").trim();
        String[] item = body.split("<item>");
        String[] subItem = item[1].split("<description>");
        String[] description = subItem[1].split("</description>");
        String[] textDescription = description[0].split("div class");
        //setOps.add("CNN_latest", textDescription);
    }

}