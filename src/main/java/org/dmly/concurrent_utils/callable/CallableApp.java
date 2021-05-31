package org.dmly.concurrent_utils.callable;

import java.util.concurrent.*;

public class CallableApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> sumResult = executorService.submit(new Sum(10));
        Future<Double> hypotResult = executorService.submit(new Hypot(3, 4));
        Future<Integer> factorialResult = executorService.submit(new Factorial(5));

        try {
            System.out.println(sumResult.get());
            System.out.println(hypotResult.get());
            System.out.println(factorialResult.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class Sum implements Callable<Integer> {
    private final int limit;

    public Sum(int limit) {
        this.limit = limit;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += i;
        }
        return sum;
    }
}

class Hypot implements Callable<Double> {
    private final double side1;
    private final double side2;

    Hypot(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public Double call() throws Exception {
        return Math.sqrt((side1*side1) + (side2 * side2));
    }
}

class Factorial implements Callable<Integer> {
    private final int limit;

    Factorial(int limit) {
        this.limit = limit;
    }

    @Override
    public Integer call() throws Exception {
        int fact = 1;

        for (int i = 2; i <= limit; i++) {
            fact *= i;
        }
        return fact;
    }
}