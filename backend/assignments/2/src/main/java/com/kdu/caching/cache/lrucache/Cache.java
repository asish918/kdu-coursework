package com.kdu.caching.cache.lrucache;

/**
 * Interface to define a generic Cache
 * @param <K> Key object
 * @param <V> Value object
 */
public interface Cache<K, V> {
    boolean put(K key, V value);

    V get(K key);

    int size();

    boolean isEmpty();

    void clear();
}
