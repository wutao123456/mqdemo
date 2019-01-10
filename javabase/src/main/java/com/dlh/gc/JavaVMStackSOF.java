package com.dlh.gc;

/**
 * @author wutao
 * @date 2019/1/10
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    /**
     * 栈溢出测试
     * @param args
     */
    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try{
            javaVMStackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length: " + javaVMStackSOF.stackLength);
            throw e;
        }
    }
}
