package org.dmly.concurrent_utils.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinApp {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.shutdown();
    }
}

class Action extends RecursiveAction {
    @Override
    protected void compute() {

    }
}

class Task extends RecursiveTask<String> {
    @Override
    protected String compute() {
        return null;
    }
}
