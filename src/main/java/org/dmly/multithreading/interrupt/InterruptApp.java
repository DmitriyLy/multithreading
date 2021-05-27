package org.dmly.multithreading.interrupt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class InterruptApp {
    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

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

        threads.add(new Thread(waitingMonitor, "Child Thread 1"));
        threads.add(new Thread(waitingMonitor, "Child Thread 2"));
        threads.add(new Thread(() -> {
            int random = -1;
            boolean interrupted = false;
            for (int l = 0; l < Integer.MAX_VALUE; l++) {
                for (int k = 0; k < Integer.MAX_VALUE; k++) {
                    random = (new Random()).nextInt();
                    if (Thread.interrupted()) {
                        System.out.println(Thread.currentThread().getName() +
                                ": interrupted, with l = " + l + ", k = " + k + " and random = " + random);
                        interrupted = true;
                        break;
                    }
                }
                if (interrupted) {
                    System.out.println("Outer break");
                    return;
                }
            }
            System.out.println(Thread.currentThread().getName() + ": finished with random = " + random);
        },"Child Thread 3 - with interrupted handling"));

        Thread controlThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String command = null;

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                try {
                    command = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if ("exit".equals(command)) {
                    if (threads.stream().anyMatch(Thread::isAlive)) {
                        System.err.println("There are alive threads");
                    } else {
                        break;
                    }
                } else if ("notify".equals(command)) {
                    synchronized (monitor) {
                        monitor.notify();
                    }
                } else if ("notifyAll".equals(command)) {
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                } else if ("interrupt".equals(command)) {
                    threads.stream()
                            .filter(Thread::isAlive)
                            .findAny()
                            .ifPresentOrElse(Thread::interrupt, () -> System.err.println("No Alive threads found"));
                } else if (command != null) {
                    System.err.println("Unknown command");
                }

            }
            System.out.println("Exiting control thread");
        }, "Control thread");


        controlThread.start();

        threads.forEach(Thread::start);

        controlThread.join();
    }
}
