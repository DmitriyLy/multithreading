package org.dmly.concurrent_utils.executor_future;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class ExecutorFutureApp {

    private static final Integer duration = 30;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> first = executorService.submit(new WaitingCallable(counter -> {
            System.out.println("First: " + counter);
        }, duration));

        Future<String> second = executorService.submit(new WaitingCallable(counter -> {
            System.out.println("Second: " + counter);
        }, duration));

        Future<String> third = executorService.submit(new WaitingCallable(counter -> {
            System.out.println("Third: " + counter);
        }, duration));

        Future<String> fourth = executorService.submit(new WaitingCallable(counter -> {
            System.out.println("Fourth: " + counter);
        }, duration));

        System.out.println("First starting......");
        System.out.println(first.get());

        System.out.println("Second starting......");
        System.out.println(second.get());

        System.out.println("Third starting......");
        System.out.println(third.get());

        System.out.println("Fourth starting......");
        System.out.println(fourth.get());

        executorService.shutdown();
    }
}

class WaitingCallable implements Callable<String> {

    private final Consumer<Integer> task;
    private final int duration;

    WaitingCallable(Consumer<Integer> task, int duration) {
        this.task = task;
        this.duration = duration;
    }

    @Override
    public String call() throws Exception {

        int counter = 0;

        while (counter < duration) {
            counter++;
            task.accept(counter);
            Thread.sleep(1000);
        }

        return Thread.currentThread().getName() + ": task finished";
    }
}
