package org.dmly.multithreading.runnable;

public class NewThread implements Runnable {

    private Thread thread;

    public NewThread() {
        thread = new Thread(this, "Demo Thread");
        System.out.println("Child thread thread: " + thread);
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

    public Thread getThread() {
        return thread;
    }
}
