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

        new Thread(() -> {
            boolean firstDone = false;
            boolean secondDone = false;
            boolean thirdDone = false;
            boolean fourthDone = false;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                while (true) {
                    if (!firstDone && (first.isDone() || first.isCancelled())) {
                        System.out.println(first.get());
                        firstDone = true;
                    }

                    if (!secondDone && (second.isDone() || second.isCancelled())) {
                        System.out.println(second.get());
                        secondDone = true;
                    }

                    if (!thirdDone && (third.isDone() || third.isCancelled())) {
                        System.out.println(third.get());
                        thirdDone = true;
                    }

                    if (!fourthDone && (fourth.isDone() || fourth.isCancelled())) {
                        System.out.println(fourth.get());
                        fourthDone = true;
                    }

                    if (firstDone && secondDone && thirdDone && fourthDone) {
                        break;
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            executorService.shutdown();

            System.out.println(Thread.currentThread().getName() + " finished.");

        }, "Watcher").start();

        System.out.println(Thread.currentThread().getName() + " finished.");
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
