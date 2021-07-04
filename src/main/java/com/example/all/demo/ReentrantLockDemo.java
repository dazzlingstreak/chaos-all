package com.example.all.demo;

import com.example.all.service.ReentrantLockService;
import com.example.all.service.ReentrantLockServiceThread;

/**
 * @author huangdawei
 * @date 2021/7/4 1:32 下午
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockService reentrantLockService = new ReentrantLockService();

        for (int i = 1; i <= 10; i++) {
            ReentrantLockServiceThread thread = new ReentrantLockServiceThread("thread" + i, reentrantLockService);
            thread.start();
        }

        Thread.sleep(40000);
        ReentrantLockServiceThread signalThread = new ReentrantLockServiceThread("thread-signal", reentrantLockService);
        signalThread.start();

    }
}
