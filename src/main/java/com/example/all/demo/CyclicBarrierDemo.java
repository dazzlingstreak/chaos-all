package com.example.all.demo;

import com.example.all.service.CyclicBarrierService;
import com.example.all.service.CyclicBarrierServiceThread;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangdawei
 * @date 2021/7/6 1:52 下午
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {

        Executor executor = Executors.newFixedThreadPool(1);

        //当CyclicBarrier的count减到0的时候，执行barrierCommand，默认是由最后一个将count减为0的线程执行
        //此处将执行barrierCommand的任务也用一个固定的线程执行，一方面可以控制最后执行的并发性（只有那个线程可以执行）
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            executor.execute(() -> System.out.println("await finish execute"));
        });

        CyclicBarrierService cyclicBarrierService = new CyclicBarrierService(cyclicBarrier);

        for (int i = 1; i <= 2; i++) {
            CyclicBarrierServiceThread thread = new CyclicBarrierServiceThread("thread-" + i, cyclicBarrierService);
            thread.start();
        }
    }
}
