package com.example.all.service;

/**
 * @author huangdawei
 * @date 2021/7/4 1:29 下午
 */
public class ReentrantLockServiceThread extends Thread {

    private ReentrantLockService reentrantLockService;

    public ReentrantLockServiceThread(String name, ReentrantLockService reentrantLockService) {
        super();
        this.setName(name);
        this.reentrantLockService = reentrantLockService;
    }


    @Override
    public void run() {
        try {
            this.reentrantLockService.doSomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
