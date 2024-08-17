package org.streams;

import java.util.stream.IntStream;

public class ForLoopByStreamRange {
    public static void main(String[] args) {
        IntStream.range(0, 20).forEach((a) -> {
            System.out.format("Hello world %d!\n", a);
        });
    }
}
