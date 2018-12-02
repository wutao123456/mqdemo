package com.dlh.generic.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 18:30
 */
public class BaseService<T> {

    @Autowired
    private BaseRepository<T> baseRepository;

    public void add(){
        System.out.println("add...");
        System.out.println(baseRepository);
    }
}
