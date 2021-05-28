package org.dmly.concurrent_utils.executor;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorApp {
    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(5);
        CountDownLatch latch2 = new CountDownLatch(5);
        CountDownLatch latch3 = new CountDownLatch(5);
        CountDownLatch latch4 = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("Starting");

        executorService.execute(getTask("A", latch1));
        executorService.execute(getTask("B", latch2));
        executorService.execute(getTask("C", latch3));
        executorService.execute(getTask("D", latch4));

        try {
            latch1.await();
            latch2.await();
            latch3.await();
            latch4.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("Done");
    }

    private static Runnable getTask(String name, CountDownLatch latch) {
        return new Runnable() {
            private String name;
            private CountDownLatch latch;

            @Override
            public void run() {
                IntStream.range(0, 5).forEach(i -> {
                    System.out.println(name + ": " + i);
                    latch.countDown();
                });
            }

            Runnable setParams(Map<String, Object> params) {
                name = (String) params.get("name");
                latch = (CountDownLatch) params.get("latch");
                return this;
            }
        }.setParams(Map.of("name", name, "latch", latch));
    }
}
