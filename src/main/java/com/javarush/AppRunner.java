package com.javarush;

public class AppRunner {
    public static void main(String[] args) {
        Application app = new Application();

        try {
            app.getAllData();
            app.compareSpeedReceivedData();
        } finally {
            app.shutdown();
        }
    }
}
