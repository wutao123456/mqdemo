package com.dlh.mw;

public class MWTest {
    public static void main(String[] args) {
        Master master = new Master(new Worker(),10);
        for(int i=1;i<=10000;i++){
            master.submit(i);
        }

        master.execute();
        long start = System.currentTimeMillis();
        while (true){
            if(master.isComplete()){
                long end = System.currentTimeMillis();
                System.out.println("最终结果为"+master.getResult()+"耗时"+(end-start));
                break;
            }
        }
    }
}
