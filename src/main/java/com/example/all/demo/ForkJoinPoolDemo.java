package com.example.all.demo;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * @author huangdawei
 * @date 2021/7/10 11:06 下午
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        StopWatch watch = StopWatch.create();
        watch.start();
        ForLoopCalculator forLoopCalculator = new ForLoopCalculator();
        long forLoopResult = forLoopCalculator.sumUp(numbers);
        watch.stop();

        System.out.println("ForLoop Time Elapsed:" + watch.getTime() + " ms");

        watch.reset();
        watch.start();
        ExecutorServiceCalculator2 executorServiceCalculator = new ExecutorServiceCalculator2();
        long executorServiceResult = executorServiceCalculator.sumUp(numbers);
        watch.stop();

        System.out.println("ExecutorService Time Elapsed:" + watch.getTime() + " ms");
    }


    interface Calculator {
        long sumUp(long[] numbers) throws InterruptedException;
    }

    static class ForLoopCalculator implements Calculator {

        @Override
        public long sumUp(long[] numbers) {
            long total = 0;
            for (long item : numbers) {
                total += item;
            }
            return total;
        }
    }

    static class ExecutorServiceCalculator implements Calculator {

        private int parallism;
        private ExecutorService executorService;

        public ExecutorServiceCalculator() {
            this.parallism = Runtime.getRuntime().availableProcessors();
            this.executorService = Executors.newFixedThreadPool(parallism);
        }

        @Override
        public long sumUp(long[] numbers) {
            //拆分到多个线程去执行任务，数据量均衡
            int part = numbers.length / parallism;
            List<Future<Long>> futures = new ArrayList<>();

            for (int i = 0; i < parallism; i++) {
                int from = i * part;
                int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1;
                SumTask sumTask = new SumTask(numbers, from, to);
                Future<Long> future = executorService.submit(sumTask);
                futures.add(future);
            }

            long total = 0l;
            for (Future<Long> future : futures) {
                try {
                    total += future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return total;
        }


        class SumTask implements Callable<Long> {

            private long[] numbers;
            private int from;
            private int to;

            public SumTask(long[] numbers, int from, int to) {
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            @Override
            public Long call() {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            }
        }
    }

    static class ExecutorServiceCalculator2 implements Calculator {

        private int parallism;
        private ExecutorService executorService;
        private ExecutorCompletionService<Long> completionService;

        public ExecutorServiceCalculator2() {
            this.parallism = Runtime.getRuntime().availableProcessors();
            this.executorService = Executors.newFixedThreadPool(parallism);
            this.completionService = new ExecutorCompletionService(this.executorService);
        }

        @Override
        public long sumUp(long[] numbers) {
            //拆分到多个线程去执行任务，数据量均衡
            int part = numbers.length / parallism;

            for (int i = 0; i < parallism; i++) {
                int from = i * part;
                int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1;
                SumTask sumTask = new SumTask(numbers, from, to);
                completionService.submit(sumTask);
            }

            long total = 0l;
            for (int i = 0; i < parallism; i++) {
                try {
                    total += completionService.take().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return total;
        }


        class SumTask implements Callable<Long> {

            private long[] numbers;
            private int from;
            private int to;

            public SumTask(long[] numbers, int from, int to) {
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            @Override
            public Long call() {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            }
        }
    }

}
