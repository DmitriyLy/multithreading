package org.dmly.concurrent_utils.semaphore;

import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphoreApp {
    public static void main(String[] args) {
        Queue queue = new Queue();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                queue.get();
            }
        }, "Consumer").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                queue.put(i);
            }
        }, "Producer").start();
    }
}

class Queue {
    private final static Semaphore consumerSemaphore = new Semaphore(0);
    private final static Semaphore producerSemaphore = new Semaphore(1);

    private int value;

    public int get() {
        try {
            consumerSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Got: " + value);
        producerSemaphore.release();
        return value;
    }

    public void put(int value) {
        try {
            producerSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.value = value;
        System.out.println("Put: " + value);
        consumerSemaphore.release();
    }
}
