package com.dlh.designpattern.factory.factory;

import com.dlh.designpattern.factory.simplefactory.Operation;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:43
 */
public interface Factory {

    Operation createOperation();
}
