package org.dmly.multithreading.sync_sleep;

public class SyncSleepApp {

    public static void main(String[] args) {

        var monitor = new Monitor();

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    monitor.getThreadName(Thread.currentThread());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread 1");
        Thread thread2 = new Thread(() -> {
            monitor.getThreadName(Thread.currentThread());
            System.out.println(Thread.currentThread().getName() + ": exiting, " + Thread.currentThread().getState());
        }, "Thread 2");

        thread1.start();
        thread2.start();

    }

}

class Monitor {
    public synchronized void getThreadName(Thread thread) {
        System.out.println(thread.getName());
    }
}
