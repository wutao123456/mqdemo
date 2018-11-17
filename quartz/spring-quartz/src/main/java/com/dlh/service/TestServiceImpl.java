package com.dlh.service;

import org.springframework.stereotype.Service;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/17 18:27
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.err.println("test service");
    }
}
