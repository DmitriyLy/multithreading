package org.dmly.concurrent_utils.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerApp {

    public static void main(String[] args) {
        var exchanger = new Exchanger<String>();
        new Thread(new UseString(exchanger)).start();
        new Thread(new MakeString(exchanger)).start();
    }
}

class MakeString implements Runnable {
    private final Exchanger<String> exchanger;
    private String string;

    MakeString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        string = new String();
    }

    @Override
    public void run() {
        char ch = 'A';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                string += ch++;
            }

            try {
                string = exchanger.exchange(string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class UseString implements Runnable {
    private final Exchanger<String> exchanger;
    private String string;

    UseString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++)  {
            try {
                string = exchanger.exchange(new String());
                System.out.println("Got: " + string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}