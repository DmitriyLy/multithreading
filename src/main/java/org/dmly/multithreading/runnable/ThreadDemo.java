package org.dmly.multithreading.runnable;

public class ThreadDemo {
    public static void main(String[] args) {
        NewThread newThread = new NewThread();
        newThread.getThread().start();

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread exiting.");
    }
}
