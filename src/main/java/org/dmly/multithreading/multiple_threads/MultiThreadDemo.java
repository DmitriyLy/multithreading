package org.dmly.multithreading.multiple_threads;

public class MultiThreadDemo {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println("Child Thread One: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "One");

        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println("Child Thread Two: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Two");

        Thread thread3 = new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println("Child Thread Three: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Three");

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
