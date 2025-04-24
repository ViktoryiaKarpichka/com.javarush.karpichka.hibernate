package com.javarush.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.config.RedisConfig;
import com.javarush.entity.redis.CityCountry;
import java.util.List;
import redis.clients.jedis.Jedis;

public class RedisDataService {
    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    public RedisDataService() {
        jedis = RedisConfig.getRedisConnection();
        objectMapper = new ObjectMapper();
    }

    public void pushToRedis(List<CityCountry> data) {
        for (CityCountry cityCountry : data) {
            try {
                jedis.set(String.valueOf(cityCountry.getId()), objectMapper.writeValueAsString(cityCountry));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void testRedisData(List<Integer> ids) {
        for (Integer id : ids) {
            String value = jedis.get(String.valueOf(id));
            try {
                objectMapper.readValue(value, CityCountry.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeRedisConnection() {
        if (jedis != null && jedis.isConnected()) {
            jedis.close();
        }
    }
}
