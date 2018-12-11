package com.dlh;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/11 20:55
 */
public class Main {

    /**
     * 编程式设置
     * @param args
     */
    public static void main(String[] args) {
        CacheManager cacheManager = new CacheManager("./src/main/resources/ehcache.xml");
        Cache cache = cacheManager.getCache("mycache");
        CacheConfiguration cacheConfiguration = cache.getCacheConfiguration();
        cacheConfiguration.setEternal(false);
        cacheConfiguration.setTimeToIdleSeconds(5);
        cacheConfiguration.setTimeToLiveSeconds(5);
        cacheConfiguration.setOverflowToDisk(true);
        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");




        CacheManager manager = CacheManager.create();
        CacheManager manager1 = CacheManager.newInstance();
        CacheManager manager2 = CacheManager.newInstance("./src/main/resources/ehcache.xml");
    }
}
