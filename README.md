# Spring Data Redis with Apache Camel RSS feed

Example project of Spring Data for Redis, in-memory data structure store.

Redis data are stored with Camel RSS component (Available as of Camel version 2.0), getting a news feed.

# Redis

Redis is an open source (BSD licensed), in-memory data structure store, used as a database, cache and message broker. It supports data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs and geospatial indexes with radius queries. 

Redis has built-in replication, Lua scripting, LRU eviction, transactions and different levels of on-disk persistence, and provides high availability via Redis Sentinel and automatic partitioning with Redis Cluster.

### Server

In this example we use Redis as a sever database in standalone mode.

### Java Redis Client / Lettuce.io

Lettuce is a fully non-blocking Redis client built with netty providing Reactive, Asynchronous and Synchronous Data Access. 

This example is building with Lettuce 3: a scalable thread-safe Java RedisClient providing both synchronous and asynchronous connections for server.

### Redis connection pool

Lettuce connections are designed to be thread-safe so one connection can be shared amongst multiple threads and Lettuce connections auto-reconnection by default. While connection pooling is not necessary in most cases it can be helpful in certain use cases.

So, you won't find any connection pool configuration in this repository.


# Apache Camel RSS Component

The rss component is used for polling RSS feeds in several threads from CNN news channel. 

Camel will default poll the feed every 60th seconds.

The component currently only supports polling (consuming) feeds.

Camel-rss internally uses a patched version of ROME hosted on ServiceMix to solve some OSGi class loading issues.


## Prerequisites

You will need following to run this application:

- [Redis 4+](https://redis.io/download)
- [Lettuce client 3](https://lettuce.io/lettuce-3/release/api/)
- [Java 8+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven 2+](https://maven.apache.org/)

## Starting the application

As first step you will need to install and start a Redis DB. 

In this project client's configuration is done for a running local Redis server.

After that you can start this application using maven: just typing:

mvn camel:run

Once feed's consumer is started Redis data are stored as Sets type in XML format.

Note 1: This is a console app

Note 2: Please be aware that, each time the process is started, all data are evicted from Redis

Note 3: You can stop Camel Consumer just typing 'Ctrl+C' at Terminal


# See Also

- [Redis](https://redis.io)
- [Lettuce client](https://lettuce.io)
- [Spring Data Redis](https://projects.spring.io/spring-data-redis/)
- [Apache Camel GitHub](https://github.com/apache/camel)
