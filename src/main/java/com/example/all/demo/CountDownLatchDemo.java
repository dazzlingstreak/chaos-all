package com.example.all.demo;

import com.example.all.service.CountDownLatchService;
import com.example.all.service.CountDownLatchServiceThread;

import java.util.concurrent.CountDownLatch;

/**
 * @author huangdawei
 * @date 2021/7/5 2:57 下午
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        CountDownLatchService countDownLatchService = new CountDownLatchService(countDownLatch);

        for (int i = 1; i <= 10; i++) {
            CountDownLatchServiceThread thread = new CountDownLatchServiceThread("thread-" + i, countDownLatchService);
            thread.start();
        }

        countDownLatch.await();

        System.out.println("finish");

    }
}
