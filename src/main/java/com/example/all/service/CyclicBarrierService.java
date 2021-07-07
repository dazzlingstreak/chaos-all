package com.example.all.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author huangdawei
 * @date 2021/7/6 2:04 下午
 */
public class CyclicBarrierService {

    private CyclicBarrier cyclicBarrier;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public CyclicBarrierService(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void doSomething() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + ":doSomething start-" + LocalDateTime.now().format(formatter));
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + ":doSomething end-" + LocalDateTime.now().format(formatter));
        cyclicBarrier.await();
    }
}
