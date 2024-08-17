package org.multithreading;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new IntCounterRunnable());
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
}
