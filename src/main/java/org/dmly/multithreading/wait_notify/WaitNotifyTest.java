package org.dmly.multithreading.wait_notify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WaitNotifyTest {

    public static void main(String[] args) throws InterruptedException {

        var monitor = new Object();

        Runnable waitingMonitor = () -> {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread() + " exiting.");
        };

        Thread thread1 = new Thread(waitingMonitor, "Thread 1");
        Thread thread3 = new Thread(waitingMonitor, "Thread 3");

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = null;
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if ("notify".equals(input)) {
                    synchronized (monitor) {
                        monitor.notify();
                    }
                } else if ("exit".equals(input)) {
                    if (thread1.isAlive() || thread3.isAlive()) {
                        System.err.println("There are alive threads");
                    } else {
                        break;
                    }
                } else if ("notifyAll".equals(input)) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                } else if (input != null) {
                    System.err.println("Unknown command");
                }
            }
            System.out.println(Thread.currentThread() + " exiting.");
        }, "Thread 2");

        thread1.start();
        thread2.start();
        thread3.start();


        thread2.join();
    }

}
