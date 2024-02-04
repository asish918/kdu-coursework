package com.kdu.smarthome.cache.lrucache;

/**
 * Generic class that defines the element to be stored in the Cache
 * @param <K> Key object
 * @param <V> Value object
 */
public class CacheElement<K, V> {
    private K key;
    private V value;

    public CacheElement(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
