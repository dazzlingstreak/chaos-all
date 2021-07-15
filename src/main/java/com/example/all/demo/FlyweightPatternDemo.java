package com.example.all.demo;

/**
 * @author huangdawei
 * @date 2021/7/13 4:29 下午
 */
public class FlyweightPatternDemo {

    public static void main(String[] args) {
        int i = 1;
        i = i++;

        System.out.println("i=" + i);

        int j=i++;
        System.out.println("j=" + j);
        System.out.println("i=" + i);

        int k=i+++i*i++;
        System.out.println("k=" + k);
        System.out.println("i=" + i);


    }

    static void update(String s) {
        s = "aidexuanwo记录记录看见了";
        System.out.println(s);
    }

    void update2(String s){
        long less = 100l;
        int more = 500;
    }
}
