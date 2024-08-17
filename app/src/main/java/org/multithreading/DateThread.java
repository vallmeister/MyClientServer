package org.multithreading;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class DateThread extends Thread {

    @Override
    public void run() {
        Stream.generate(LocalDateTime::now).limit(20).forEach(System.out::println);
    }
}
