package com.example.all.demo;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author huangdawei
 * @date 2021/7/10 9:39 下午
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-step1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + "-step2");
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + "-step3");
        }).whenComplete((t, u) -> {
            System.out.println(Thread.currentThread().getName() + "-step4");
        });


        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-step11");
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + "-step22");
        }).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-step33");
        }).whenComplete((t, u) -> {
            System.out.println(Thread.currentThread().getName() + "-step44");
        });

        Thread.sleep(1000);
    }

}
