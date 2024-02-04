package com.kdu.smarthome.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;

/**
 * My Custom Cache that implements the Cache interface of Spring Framework
 * and uses the {@link com.kdu.smarthome.cache.LRUCache LRUCache}
 */
public class CustomCache implements Cache {

    private final String name;
    private final LRUCache<Object, Object> cache;

    public CustomCache(String name, int maxSize) {
        this.name = name;
        this.cache = new LRUCache<>(maxSize);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object value = cache.get(key);
        return (value != null) ? new SimpleValueWrapper(value) : null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = cache.get(key);
        if (type != null && type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Object value = cache.get(key);
        if (value == null) {
            try {
                value = valueLoader.call();
                put(key, value);
            } catch (Exception e) {
                throw new ValueRetrievalException(key, valueLoader, e);
            }
        }
        return (T) value;
    }
}