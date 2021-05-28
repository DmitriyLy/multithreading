package org.dmly.concurrent_utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

public class SemaphoreApp {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(new Executor("Incrementing", semaphore, () -> SharedResource.count++), "Incrementing")
                .start();
        new Thread(new Executor("Decrementing", semaphore, () -> SharedResource.count--), "Decrementing")
                .start();
    }
}

class Executor implements Runnable {

    private final String name;
    private final Semaphore semaphore;
    private final Runnable function;

    Executor(String name, Semaphore semaphore, Runnable function) {
        this.name = name;
        this.semaphore = semaphore;
        this.function = function;
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);

        try {
            System.out.println(name + " is waiting for a permit.");
            semaphore.acquire();
            System.out.println(name + " got a permit.");

            for (int i = 0; i < 5; i++) {
                function.run();
                System.out.println(name + ": " + SharedResource.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " releases the permit.");
        semaphore.release();
    }
}

class SharedResource {
    static int count = 0;
}
