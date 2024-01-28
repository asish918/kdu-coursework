package com.kdu.caching.lrucache;

import org.junit.jupiter.api.Test;

import com.kdu.caching.cache.LRUCache;
import static org.junit.jupiter.api.Assertions.*;

/**
 * These are tests for my Custom Cache which showcases the eviction policy LRU.
 */
class LRUCacheUnitTest {

    @Test
    void addSomeDataToCache_WhenGetData_ThenIsEqualWithCacheElement() {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("1", "test1");
        lruCache.put("2", "test2");
        lruCache.put("3", "test3");
        assertSame("test1", lruCache.get("1"));
        assertSame("test2", lruCache.get("2"));
        assertSame("test3", lruCache.get("3"));
    }

    /**
     * LRU is specifically tested over here
     */
    @Test
    void addDataToCacheToTheNumberOfSize_WhenAddOneMoreData_ThenLeastRecentlyDataWillEvict() {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("1", "test1");
        lruCache.put("2", "test2");
        lruCache.put("3", "test3");
        lruCache.put("4", "test4");
        assertNotEquals("test1", lruCache.get("1"));
    }
}
