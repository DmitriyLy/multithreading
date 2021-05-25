package org.dmly.multithreading.start;

public class CurrentThread {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        System.out.println("Current thread: " + thread);

        thread.setName("This current thread");
        System.out.println("New name set: " + thread);

        try {
            for (int n = 5; n > 0; n--) {
                System.out.println(n);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
