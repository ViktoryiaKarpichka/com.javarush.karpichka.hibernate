package com.javarush.config;

import redis.clients.jedis.Jedis;

public class RedisConfig {
    private static RedisConfig instance;
    private final Jedis jedis;

    private RedisConfig() {
        jedis = new Jedis("localhost", 6379);
        jedis.connect();
    }

    public static Jedis getRedisConnection() {
        if (instance == null) {
            instance = new RedisConfig();
        }
        return instance.jedis;
    }
}
