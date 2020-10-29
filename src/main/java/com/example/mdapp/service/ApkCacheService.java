package com.example.mdapp.service;

import com.example.mdapp.entity.Apk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ApkCacheService {

    private final RedisTemplate redisTemplate;

    private Gson gson = new GsonBuilder().create();

    private static String key = "S:apk:s:";

    public ApkCacheService(@Qualifier("redisTemplate") RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public Apk get(String id){
        var ops = redisTemplate.opsForValue();

        var obj = ops.get(key.toLowerCase()+id.toLowerCase());

        if (obj != null) {
            return gson.fromJson(String.valueOf(obj), new TypeToken<Apk>() {
            }.getType());
        }

        return null;
    }

    /**
     * 添加缓存
     */
    public void set(String id, Apk entity) {

        long time=10;
        var ops = redisTemplate.opsForValue();

        ops.set(key.toLowerCase()+id.toLowerCase(), gson.toJson(entity),time, TimeUnit.MINUTES);
    }

    public boolean delete(String id){
        //var ops= redisTemplate.opsForValue();
        String relkey=key.toLowerCase()+id.toLowerCase();

        return redisTemplate.delete(relkey);
    }
}
