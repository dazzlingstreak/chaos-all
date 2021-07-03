package com.example.all.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

/**
 * @author huangdawei
 * @date 2021/7/2 3:26 下午
 */
public class SemaphoreService {

    private Semaphore semaphore;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public SemaphoreService(int permits) {
        semaphore = new Semaphore(permits);
    }

    public void doSomething() throws InterruptedException {
        semaphore.acquire();

        try {
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + LocalDateTime.now().format(formatter));
            Thread.sleep(20000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + LocalDateTime.now().format(formatter));
        } finally {
            semaphore.release();
        }
    }
}
