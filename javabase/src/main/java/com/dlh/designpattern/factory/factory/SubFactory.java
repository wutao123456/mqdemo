package com.dlh.designpattern.factory.factory;

import com.dlh.designpattern.factory.simplefactory.Operation;
import com.dlh.designpattern.factory.simplefactory.Sub;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:45
 */
public class SubFactory implements Factory {
    @Override
    public Operation createOperation() {
        return new Sub();
    }
}
