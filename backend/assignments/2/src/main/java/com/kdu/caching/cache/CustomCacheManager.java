package com.kdu.caching.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * My Custom Cache Manager that implements the Spring CacheManager interface
 * and initialises a {@link com.kdu.caching.cache.CustomCache Custom Cache}
 */
public class CustomCacheManager implements CacheManager {

    private final Map<String, Cache> caches = new HashMap<>();

    @Override
    public Cache getCache(String name) {
        return caches.computeIfAbsent(name, cacheName -> new CustomCache(name, 3));
    }

    @Override
    public Collection<String> getCacheNames() {
        return caches.keySet();
    }
}