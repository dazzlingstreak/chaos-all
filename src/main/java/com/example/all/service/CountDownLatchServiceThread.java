package com.example.all.service;

/**
 * @author huangdawei
 * @date 2021/7/5 2:58 下午
 */
public class CountDownLatchServiceThread extends Thread {

    private CountDownLatchService service;

    public CountDownLatchServiceThread(String name,CountDownLatchService service){
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
        }
    }
}
