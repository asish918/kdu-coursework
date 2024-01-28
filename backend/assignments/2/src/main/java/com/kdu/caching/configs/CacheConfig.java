package com.kdu.caching.configs;

import com.kdu.caching.cache.CustomCacheManager;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean for my {@link com.kdu.caching.cache.CustomCacheManager Custom Cache Manager} which implements
 * LRU Cache that I have manually implemented instead of using the default Cache.
 * Checkout the src/cache folder.
 */
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new CustomCacheManager();
    }
}
