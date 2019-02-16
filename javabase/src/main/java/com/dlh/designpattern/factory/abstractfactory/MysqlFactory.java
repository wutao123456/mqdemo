package com.dlh.designpattern.factory.abstractfactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:53
 */
public class MysqlFactory implements SqlFactory {
    @Override
    public IUser createUser() {
        return new MysqlUser();
    }
}
