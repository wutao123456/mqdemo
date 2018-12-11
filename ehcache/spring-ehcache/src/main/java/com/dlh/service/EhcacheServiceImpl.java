package com.dlh.service;

import com.dlh.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/11 21:39
 */
@Service
public class EhcacheServiceImpl implements EhcacheService {

    @Cacheable(value = "HelloWorldCache",key = "#param")
    @Override
    public String getTimestamp(String param) {
        Long timeStamp = System.currentTimeMillis();
        return timeStamp.toString();
    }

    @Cacheable(value = "HelloWorldCache",key = "#key")
    @Override
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据...");
        return key + ":" + String.valueOf(Math.round(Math.random()*1000000));
    }

    @CacheEvict(value = "HelloWorldCache",key = "#key")
    @Override
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据");
    }

    @CachePut(value = "HelloWorldCache",key = "#key")
    @Override
    public String refreshData(String key) {
        System.out.println("模拟从数据库中加载数据");
        return key + "::" + String.valueOf(Math.round(Math.random()*1000000));
    }

    @Override
    public User findById(String userId) {
        return null;
    }

    @Override
    public boolean isReserved(String userId) {
        return false;
    }

    @Override
    public void removeUser(String userId) {

    }

    @Override
    public void removeAllUser() {

    }
}
