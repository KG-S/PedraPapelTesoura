package org.example;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado...");

            Socket j1 = servidor.accept();
            Socket j2 = servidor.accept();

            BufferedReader in1 = new BufferedReader(new InputStreamReader(j1.getInputStream()));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(j2.getInputStream()));

            PrintWriter out1 = new PrintWriter(j1.getOutputStream(), true);
            PrintWriter out2 = new PrintWriter(j2.getOutputStream(), true);

            out1.println("Jogo iniciado!");
            out2.println("Jogo iniciado!");

            while (true) {

                String p1 = in1.readLine().toUpperCase();
                String p2 = in2.readLine().toUpperCase();

                String resultado = resultado(p1, p2);

                out1.println("RESULTADO: " + resultado);
                out2.println("RESULTADO: " + resultado);

                out1.println("JOGO NOVO");
                out2.println("JOGO NOVO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String resultado(String a, String b) {

        if (a.equals(b))
            return "EMPATE (" + a + " x " + b + ")";

        if ((a.equals("PEDRA") && b.equals("TESOURA")) ||
                (a.equals("PAPEL") && b.equals("PEDRA")) ||
                (a.equals("TESOURA") && b.equals("PAPEL"))) {
            return "Jogador 1 venceu (" + a + " x " + b + ")";
        }

        return "Jogador 2 venceu (" + b + " x " + a + ")";
    }
}