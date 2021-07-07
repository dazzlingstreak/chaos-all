package com.example.all.service;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author huangdawei
 * @date 2021/7/6 2:03 下午
 */
public class CyclicBarrierServiceThread  extends Thread{

    private CyclicBarrierService service;

    public CyclicBarrierServiceThread(String name,CyclicBarrierService service){
        super();
        this.setName(name);
        this.service = service;
    }

    @Override
    public void run() {
        try {
            this.service.doSomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
