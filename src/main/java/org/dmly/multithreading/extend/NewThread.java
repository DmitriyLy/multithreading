package org.dmly.multithreading.extend;

public class NewThread extends Thread {

    public NewThread() {
        super("Demo Thread");
        System.out.println("Child thread: " + this);
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting child thread.");
    }
}
