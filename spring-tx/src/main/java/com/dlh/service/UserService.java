package com.dlh.service;

import com.dlh.entity.User;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/3 23:20
 */
public interface UserService {

    User findByName(String name);
    void save(User user);

    void test(User user);

    void test1(User user);
}
