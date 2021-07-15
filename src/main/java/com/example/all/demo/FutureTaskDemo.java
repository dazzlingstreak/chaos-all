package com.example.all.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author huangdawei
 * @date 2021/7/8 5:25 下午
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Student zhangsan = new Student("zhangsan", 20);
        Future<?> future = executorService.submit(new Task(zhangsan));

        future.get();

        System.out.println(zhangsan);
    }


    static class Task implements Runnable {

        private Student stu;

        public Task(Student stu) {
            this.stu = stu;
        }

        @Override
        public void run() {

            System.out.println("modify stu...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stu.setName("lisi");
        }
    }

    static class Student {
        private String name;
        private Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
