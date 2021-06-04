package org.dmly.concurrent_utils.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        double[] nums = new double[100000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }

        System.out.println("A portion of the original sequence: ");

        for (int i = 0; i < 10; i++) {
            System.out.print(nums[i] + "     ");
        }
        System.out.println("\n");

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        forkJoinPool.invoke(task);

        System.out.println("A portion of the transformed sequence (to four decimal places): ");
        for (int i = 0; i < 10; i++) {
            System.out.format("%.4f  ", nums[i]);
        }
        System.out.println("\n");
    }
}

class SqrtTransform extends RecursiveAction {

    private final int seqThreshold = 1000;

    private final double[] data;
    private final int start;
    private final int end;

    SqrtTransform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));
        }
    }
}