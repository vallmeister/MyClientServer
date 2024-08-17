package org.multithreading;

import java.util.stream.IntStream;

public class IntCounterRunnable implements Runnable {

    @Override
    public void run() {
        IntStream.range(0, 20).forEach(System.out::println);
    }
}
