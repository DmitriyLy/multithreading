package org.dmly.concurrent_utils.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RunTaskDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        double[] nums = new double[5000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = ((i % 2) == 0) ? i : -i;
        }

        Sum task = new Sum(nums, 0, nums.length);

        double summation = forkJoinPool.invoke(task);

        System.out.println("Summation: " + summation);
    }
}

class Sum extends RecursiveTask<Double> {

    private final int seqThreshold = 500;

    private final double[] data;
    private final int start;
    private final int end;

    Sum(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Double compute() {
        double sum = 0;

        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            int middle = (start + end) / 2;

            Sum subTaskA = new Sum(data, start, middle);
            Sum subTaskB = new Sum(data, middle, end);

            subTaskA.fork();
            subTaskB.fork();

            sum = subTaskA.join() + subTaskB.join();
        }

        return sum;
    }
}
