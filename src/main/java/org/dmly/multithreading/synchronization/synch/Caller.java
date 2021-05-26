package org.dmly.multithreading.synchronization.synch;

public class Caller implements Runnable{
    private String msg;
    final private Callme target;
    private Thread thread;

    public Caller(String msg, Callme target) {
        this.msg = msg;
        this.target = target;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        synchronized (target) {
            target.call(msg);
        }
    }

    public Thread getThread() {
        return thread;
    }
}
