package com.example.all.service;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author huangdawei
 * @date 2021/7/16 4:34 下午
 */
@Slf4j
public class RateLimiterChaosThread extends Thread {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private RateLimiterChaosService service;

    public RateLimiterChaosThread(String name, RateLimiterChaosService service) {
        super();
        this.setName(name);
        this.service = service;
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + ":permitApply -" + LocalDateTime.now().format(formatter));

        service.permitApply();

        log.info(Thread.currentThread().getName() + ":permitSuccess -" + LocalDateTime.now().format(formatter));

  }
}
