package org.dmly.concurrent_utils.fork_join;

import java.util.Properties;

public class AvailableProcessorDisplayer {
    public static void main(String[] args) {
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
        System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
        Properties properties = System.getProperties();
        System.out.println(properties);
    }
}
