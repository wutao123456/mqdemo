package com.dlh;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        CacheManager cacheManager = new CacheManager("./src/main/resources/ehcache.xml");
        String[] cacheNames = cacheManager.getCacheNames();
        for(String name:cacheNames){
            System.out.println(name);
        }
        Cache cache = cacheManager.getCache("HelloWorldCache");
        Element element = new Element("key1","value1");
        cache.put(element);

        Element key1 = cache.get("key1");
        System.out.println(key1);
        System.out.println(key1.getObjectValue());
        System.out.println(cache.getSize());

        cache.remove("key1");
        System.out.println(cache.get("key1"));
    }
}
