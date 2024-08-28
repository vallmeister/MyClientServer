package org.multithreading;

import java.util.stream.IntStream;

public class IntCounterRunnable implements Runnable {
    private int start;
    private int end;

    public IntCounterRunnable(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        IntStream.range(start, end).forEach(System.out::println);
    }
}
