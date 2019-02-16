package com.dlh.designpattern.factory.abstractfactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:52
 */
public class OracleUser implements IUser {
    @Override
    public void insert(User user) {
        System.out.println("在oracle中的user表中插入一条元素");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在oracle中的user表得到id为"+id+"的一条数据");
        return null;
    }
}
