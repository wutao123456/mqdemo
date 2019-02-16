package com.dlh.designpattern.factory.abstractfactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:49
 */
public interface IUser {

    void insert(User user);

    User getUser(int id);
}
