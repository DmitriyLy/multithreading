package org.dmly.multithreading.synchronization.not_sync;

public class Caller implements Runnable{
    private String msg;
    private Callme target;
    private Thread thread;

    public Caller(String msg, Callme target) {
        this.msg = msg;
        this.target = target;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        target.call(msg);
    }

    public Thread getThread() {
        return thread;
    }
}
