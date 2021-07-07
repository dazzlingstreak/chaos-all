package com.example.all.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * @author huangdawei
 * @date 2021/7/5 2:51 下午
 */
public class CountDownLatchService {

    private CountDownLatch latch;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public CountDownLatchService(CountDownLatch latch) {
        this.latch = latch;
    }

    public void doSomething() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ":doSomething start-" + LocalDateTime.now().format(formatter));
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + ":doSomething end-" + LocalDateTime.now().format(formatter));

        latch.countDown();
    }

}
