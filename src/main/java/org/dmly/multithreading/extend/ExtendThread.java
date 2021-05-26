package org.dmly.multithreading.extend;

public class ExtendThread {
    public static void main(String[] args) {
        NewThread newThread = new NewThread();

        newThread.start();

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
