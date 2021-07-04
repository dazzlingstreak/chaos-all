package com.example.all.demo;

import com.example.all.service.SemaphoreServiceThread;
import com.example.all.service.SemaphoreService;

/**
 * @author huangdawei
 * @date 2021/7/2 4:55 下午
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreService semaphoreService = new SemaphoreService(1);

        for (int i = 0; i < 10; i++) {
            SemaphoreServiceThread thread = new SemaphoreServiceThread("thread" + (i + 1), semaphoreService);
            thread.start();
        }
    }
}
