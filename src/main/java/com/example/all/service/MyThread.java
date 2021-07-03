package com.example.all.service;

/**
 * @author huangdawei
 * @date 2021/7/2 4:53 下午
 */
public class MyThread extends Thread {

    private SemaphoreService semaphoreService;

    public MyThread(String name, SemaphoreService semaphoreService) {
        super();
        this.setName(name);
        this.semaphoreService = semaphoreService;
    }

    @Override
    public void run() {
        try {
            this.semaphoreService.doSomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
