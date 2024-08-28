package org.multithreading;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        simpleThreads();
        simpleThreadPool();
        simpleCallable();
    }

    private static void simpleThreads() throws InterruptedException {
        Thread t1 = new Thread(new IntCounterRunnable(0, 20));
        Thread t2 = new DateThread();
        System.out.println(Thread.currentThread().getName());
        System.out.printf("Thread %s starting\n", t2.getName());
        t2.start();
        System.out.printf("Thread %s starting\n", t1.getName());
        t1.start();
        t1.join();
        t2.join();
        System.out.println("Finished all threads");
    }

    private static void simpleThreadPool() throws InterruptedException {
        final int cores = Runtime.getRuntime().availableProcessors();
        System.out.printf("We use %d threads.\n", cores);
        final ExecutorService executor = Executors.newFixedThreadPool(cores);
        Runnable r1 = new IntCounterRunnable(0, 20);
        Runnable r2 = new IntCounterRunnable(100, 120);
        executor.execute(r1);
        executor.execute(r2);
        Thread.sleep(500);
        executor.execute(r1);
        executor.execute(r2);
        executor.shutdown();
    }

    private static void simpleCallable() {
        byte[] b = new byte[4_000_000];
        new Random().nextBytes(b);
        Callable<byte[]> callable = new SorterCallable(b);
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<byte[]> result = executor.submit(callable);
        try {
            byte[] bs = result.get();
            System.out.printf("%d, %d, %d\n", bs[0], bs[1], bs[bs.length - 1]);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
