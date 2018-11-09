package com.dlh.designpattern.facade;

public class Computer {

    private CPU cpu;

    private Memory memory;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
    }

    public void start(){
        cpu.start();
        memory.start();
    }

    public void shutdown(){
        cpu.shutdown();
        memory.shutdown();
    }
}
