package com.example.all.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangdawei
 * @date 2021/7/4 1:23 下午
 */
public class ReentrantLockService {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile int count = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public void doSomething() throws InterruptedException {

        lock.lock();
        try {

            System.out.println("execute times:" + count + "," + Thread.currentThread().getName() + " execute");

            while (count == 1 && Objects.equals(Thread.currentThread().getName(), "thread-signal")) {
                count++;
                condition.signal();
            }
            while (count == 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + LocalDateTime.now().format(formatter));
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + LocalDateTime.now().format(formatter));

            count++;
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        condition.signal();
    }
}
