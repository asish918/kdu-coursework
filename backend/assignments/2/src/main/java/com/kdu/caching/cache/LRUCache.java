package com.kdu.caching.cache;

import com.kdu.caching.cache.lrucache.Cache;
import com.kdu.caching.cache.lrucache.CacheElement;
import com.kdu.caching.cache.lrucache.DoublyLinkedList;
import com.kdu.caching.cache.lrucache.LinkedListNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simple LRU Cache. A standard DSA problem which has been modularised
 * according to the needs of the Assignment. The Cache is thread-safe and
 * all of its operations use the ReentrantReadWriteLock()
 * @param <K> Key Object
 * @param <V> Value Object
 */
public class LRUCache<K, V> implements Cache<K, V> {
    private int size;
    private Map<K, LinkedListNode<CacheElement<K, V>>> linkedListNodeMap;
    private DoublyLinkedList<CacheElement<K, V>> doublyLinkedList;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int size) {
        this.size = size;
        this.linkedListNodeMap = new ConcurrentHashMap<>(size);
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    public void remove(K key) {
        this.lock.writeLock().lock();
        try {
            LinkedListNode<CacheElement<K, V>> linkedListNode = this.linkedListNodeMap.get(key);
            if (linkedListNode != null && !linkedListNode.isEmpty()) {
                linkedListNodeMap.remove(key);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public boolean put(K key, V value) {
        this.lock.writeLock().lock();
        try {
            CacheElement<K, V> item = new CacheElement<>(key, value);
            LinkedListNode<CacheElement<K, V>> newNode;
            if (this.linkedListNodeMap.containsKey(key)) {
                LinkedListNode<CacheElement<K, V>> node = this.linkedListNodeMap.get(key);
                newNode = doublyLinkedList.updateAndMoveToFront(node, item);
            } else {
                if (this.size() >= this.size) {
                    this.evictElement();
                }
                newNode = this.doublyLinkedList.add(item);
            }
            if (newNode.isEmpty()) {
                return false;
            }
            this.linkedListNodeMap.put(key, newNode);
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public V get(K key) {
        this.lock.readLock().lock();
        try {
            LinkedListNode<CacheElement<K, V>> linkedListNode = this.linkedListNodeMap.get(key);
            if (linkedListNode != null && !linkedListNode.isEmpty()) {
                linkedListNodeMap.put(key, this.doublyLinkedList.moveToFront(linkedListNode));
                return linkedListNode.getElement().getValue();
            }
            return null;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public int size() {
        this.lock.readLock().lock();
        try {
            return doublyLinkedList.size();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        this.lock.writeLock().lock();
        try {
            linkedListNodeMap.clear();
            doublyLinkedList.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    private boolean evictElement() {
        this.lock.writeLock().lock();
        try {
            LinkedListNode<CacheElement<K, V>> linkedListNode = doublyLinkedList.removeTail();
            if (linkedListNode.isEmpty()) {
                return false;
            }
            linkedListNodeMap.remove(linkedListNode.getElement().getKey());
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
