package com.wutao.lock;

/**
 * @author wutao
 * @date 2019/12/23
 * 父子继承关系时，子类也可以通过“可重入锁”调用父类的同步方法。
 */
public class FatherClass {

    public int i = 10;

    public synchronized void operateIMainMethod(){
        try {
            i--;
            System.out.println("father class print i = " + i);
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FatherSunThread t = new FatherSunThread();
        t.start();
    }

}

class SubClass extends FatherClass{

    public synchronized void operateISubMethod(){
        try {
            while (i > 0){
                i--;
                System.out.println("sub class print i = " + i);
                Thread.sleep(100);
                this.operateIMainMethod();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class FatherSunThread extends Thread{
    @Override
    public void run() {
        super.run();
        SubClass subClass = new SubClass();
        subClass.operateISubMethod();
    }
}
