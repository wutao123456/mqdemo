package com.dlh.designpattern.factory.factory;

import com.dlh.designpattern.factory.simplefactory.Add;
import com.dlh.designpattern.factory.simplefactory.Operation;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:45
 */
public class AddFactory implements Factory {
    @Override
    public Operation createOperation() {
        return new Add();
    }
}
