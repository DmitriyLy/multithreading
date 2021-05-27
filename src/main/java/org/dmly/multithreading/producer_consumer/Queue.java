package org.dmly.multithreading.producer_consumer;

public class Queue {

    private int value;
    private boolean valueSet = false;

    public synchronized int get() {

        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Got: " + value);
        valueSet = false;
        notify();
        return value;
    }

    public synchronized void put(int value) {

        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.value = value;
        valueSet = true;
        notify();
        System.out.println("Put: " + value);
    }
}
