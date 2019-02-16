package com.dlh.designpattern.template;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 14:44
 * 模板方法模式
 */
public class Client {

    public static void main(String[] args) {

        CarModel ocar = new OCar();
        ocar.excet();

        CarModel wcar = new WCar();
        wcar.excet();
    }
}
