package com.example.all.demo;

import com.example.all.service.MyThread;
import com.example.all.service.SemaphoreService;

/**
 * @author huangdawei
 * @date 2021/7/2 4:55 下午
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreService semaphoreService = new SemaphoreService(2);

        for (int i = 0; i < 10; i++) {
            MyThread thread = new MyThread("thread" + (i + 1), semaphoreService);
            thread.start();
        }
    }
}
