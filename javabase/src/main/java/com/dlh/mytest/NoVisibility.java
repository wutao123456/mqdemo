package com.dlh.mytest;

public class NoVisibility{

    private static int number;

    private  volatile static boolean ready;

    private  static class TestThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }


    public  static void main(String[] args) {
        new TestThread().start();
        number = 24;
        ready = true;
    }
}
