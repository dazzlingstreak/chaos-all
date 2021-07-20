package com.example.all.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static org.apache.commons.lang3.math.NumberUtils.max;
import static org.apache.commons.lang3.math.NumberUtils.min;

/**
 * @author huangdawei
 * @date 2021/7/16 4:35 下午
 */
public class RateLimiterChaosService {

    //令牌桶中的当前令牌总数
    long storedPermits = 0;
    //令牌桶的最大容量
    final long maxPermits = 5;
    //发放令牌间隔：纳秒
    final long interval = 1000_000_000;
    //令牌桶中的当前令牌总数 对应的计算时间
    long permitsBirthday = System.nanoTime();

    /**
     * 同步下令牌桶中当前的令牌总数
     *
     * @param acquireTime
     */
    private void syncPermits(long acquireTime) {
        if (acquireTime <= permitsBirthday) {
            return;
        }
        long newPermits = (acquireTime - permitsBirthday) / interval;
        storedPermits = min(maxPermits, storedPermits + newPermits);
        permitsBirthday = acquireTime;
    }

    /**
     * 获取令牌，返回可以获取到令牌的时间
     *
     * @param acquireTime
     * @return
     */
    public synchronized long tryAcquire(long acquireTime) {
        syncPermits(acquireTime);

        long permitGet = permitsBirthday;

        if (storedPermits > 0) {
            storedPermits -= 1;
        } else {
            permitGet = permitGet + interval;
        }

        return permitGet;
    }

    public void permitApply() {
        long now = System.nanoTime();
        long permitGet = tryAcquire(now);
        long waitTime = max(permitGet - now, 0);

        while (waitTime > 0) {

            LockSupport.parkNanos(waitTime);
//                TimeUnit.NANOSECONDS.sleep(waitTime);

            now = System.nanoTime();
            permitGet = tryAcquire(now);
            waitTime = max(permitGet - now, 0);
        }

    }
}
