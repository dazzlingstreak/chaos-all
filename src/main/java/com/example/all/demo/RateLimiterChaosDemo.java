package com.example.all.demo;

import com.example.all.service.RateLimiterChaosService;
import com.example.all.service.RateLimiterChaosThread;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.math.NumberUtils.max;
import static org.apache.commons.lang3.math.NumberUtils.min;

/**
 * @author huangdawei
 * @date 2021/7/16 3:57 下午
 */
public class RateLimiterChaosDemo {

    public static void main(String[] args) throws InterruptedException {
        RateLimiterChaosService service = new RateLimiterChaosService();

        Thread[] threads = new Thread[10];
        for (int i = 1; i <= 10; i++) {
            threads[i - 1] = new RateLimiterChaosThread("thread" + i, service);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        //todo: 问题存在-运行后并不会在等待时间结束后，能全部执行完。原因？？
    }


}
