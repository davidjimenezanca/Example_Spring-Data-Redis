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
package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

@Ignore // enable me to run this test as well so we can cover testing the route
public class FooApplicationTest extends CamelSpringTestSupport {

    private CamelContext camelContext;

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/spring-context.xml");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        camelContext = context;
    }

    @Test
    public void shoulGetFooExchange() {

        NotifyBuilder notify = new NotifyBuilder(camelContext)
                                        .from("CNN:latest")
                                        .whenDone(1)
                                        .create();

        // Introduce a timeout as a delay in case net too low
        notify.matches(5, TimeUnit.SECONDS);
        assertNotNull("Should be done", notify);
    }

}