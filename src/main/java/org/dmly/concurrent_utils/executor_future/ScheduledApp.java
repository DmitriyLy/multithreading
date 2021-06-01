package org.dmly.concurrent_utils.executor_future;

import java.time.LocalTime;
import java.util.concurrent.*;

public class ScheduledApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("App started at " + LocalTime.now());

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Callable<String> task1 = () -> "task1 done at " + LocalTime.now();
        Callable<String> task2 = () -> "task2 done at " + LocalTime.now();

        ScheduledFuture<String> first = executorService.schedule(task1, 10, TimeUnit.SECONDS);
        ScheduledFuture<String> second = executorService.schedule(task2, 5, TimeUnit.SECONDS);

        System.out.println(first.get());
        System.out.println(second.get());

        executorService.shutdown();

        System.out.println("App ended at " + LocalTime.now());
    }
}
