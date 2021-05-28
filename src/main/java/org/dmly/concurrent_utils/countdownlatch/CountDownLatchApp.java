package org.dmly.concurrent_utils.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchApp {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);

        System.out.println("Starting");

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                latch.countDown();
            }
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
