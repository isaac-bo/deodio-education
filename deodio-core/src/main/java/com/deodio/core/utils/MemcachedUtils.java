package com.deodio.core.utils;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;


@Service(value = "cacheUtils")
public class MemcachedUtils {

   @Autowired(required = false)
    private MemCachedClient memcachedClient;

    public static final int FLAG_ADD_OR_UPDATE = 1;
    public static final int FLAG_DELETE = 2;

    public Boolean delete(String key) {

        return memcachedClient.delete(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        try {
            return (T) memcachedClient.get(key);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public boolean keyExists(String key) {
        return memcachedClient.keyExists(key);
    }

    public boolean set(String key, Object value, int expiredTime) {
        if (expiredTime > 0) {
            Date expiry = new Date(System.currentTimeMillis() + expiredTime * 60 * 1000);
            return memcachedClient.set(key, value , expiry);
        }
        return memcachedClient.set(key, value);
    }

    public boolean set(String key, Object value) {
        return memcachedClient.set(key, value);
    }

    /**
     * synchronized update memcache date
     * 
     * @param storeName
     * @param key
     * @param value
     * @param flag
     *            1:add or update 2:delete
     */
    public synchronized void updateMemcache(String storeName, String key, Object value, int flag) {
        HashMap<String, Object> map = this.get(storeName);
        if (flag == 1) {
            if (null == map) {
                map = new HashMap<String, Object>();
            }
            map.put(key, value);
        } else {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
        this.set(storeName, map);
    }
}
