package org.dmly.multithreading.synchronization.synch;

public class Synch {
    public static void main(String[] args) throws InterruptedException {
        Callme target = new Callme();
        Caller caller1 = new Caller("Hello", target);
        Caller caller2 = new Caller("Synchronized", target);
        Caller caller3 = new Caller("World", target);

        caller1.getThread().setPriority(10);
        caller2.getThread().setPriority(9);
        caller3.getThread().setPriority(8);

        caller1.getThread().start();
        caller1.getThread().join();

        caller2.getThread().start();
        caller2.getThread().join();

        caller3.getThread().start();
        caller3.getThread().join();
    }
}
