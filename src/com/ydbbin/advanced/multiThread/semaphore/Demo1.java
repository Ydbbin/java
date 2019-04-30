package com.ydbbin.advanced.multiThread.semaphore;

import java.util.concurrent.Semaphore;

public class Demo1 {
    private static final Semaphore userFul, userLess;
    static{
        userFul = new Semaphore(10);
        userLess = new Semaphore(0);
    }
    public static void main(String[] args){
        System.out.println(1);
        try {
            userFul.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
