package org.example;

public class Main {

    public static void main(String[] args) {

        new Thread(() -> Servidor.main(null)).start();

        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }

        ClienteGUI.main(null);
        ClienteGUI.main(null);
    }
}