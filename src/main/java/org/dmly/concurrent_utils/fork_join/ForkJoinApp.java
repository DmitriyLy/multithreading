package org.dmly.concurrent_utils.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinApp {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " started: " + System.nanoTime());

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.submit(new Action());
        forkJoinPool.submit(new Task());

        forkJoinPool.execute(new Action());
        forkJoinPool.execute(new Task());

        forkJoinPool.execute(new Action());
        forkJoinPool.execute(new Task());

        Thread.sleep(5000);

        forkJoinPool.shutdown();

        System.out.println(Thread.currentThread().getName() + " finished: " + System.nanoTime());
    }
}

class Action extends RecursiveAction {
   private boolean child;

    public Action() {
    }

    public Action(boolean child) {
        this.child = child;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName() + " started: " + System.nanoTime());

        if (!child) {
            (new Action(true)).fork();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " finished: " + System.nanoTime());
    }
}

class Task extends RecursiveTask<String> {
    private boolean child;

    public Task() {
    }

    public Task(boolean child) {
        this.child = child;
    }

    @Override
    protected String compute() {
        System.out.println(Thread.currentThread().getName() + " started: " + System.nanoTime());

        if (!child) {
            (new Task(true)).fork();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " finished: " + System.nanoTime());
        return null;
    }
}
