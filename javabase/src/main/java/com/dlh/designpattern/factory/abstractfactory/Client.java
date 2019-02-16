package com.dlh.designpattern.factory.abstractfactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:54
 * 抽象工厂模式
 */
public class Client {

    public static void main(String[] args) {
        SqlFactory sqlFactory = new MysqlFactory();
        IUser user = sqlFactory.createUser();
        user.getUser(1);
        user.insert(new User());
    }
}
