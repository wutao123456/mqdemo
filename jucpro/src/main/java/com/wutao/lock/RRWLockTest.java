package com.wutao.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wutao
 * @date 2019/12/24
 */
public class RRWLockTest {

    private Map<String,Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock r = rwl.readLock();

    private ReentrantReadWriteLock.WriteLock w = rwl.writeLock();

    public Object get(String key){
        try {
            r.lock();
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public String[] allKeys(){
        try {
            r.lock();
            return (String[]) map.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public void set(String key,Object value){
        try {
            w.lock();
            map.put(key,value);
        } finally {
            w.unlock();
        }
    }

    public void clear(){
        try {
            w.lock();
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
