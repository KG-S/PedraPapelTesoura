package org.example;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado...");
            System.out.println("Aguardando jogador 1...");

            Socket jogador1 = servidor.accept();
            System.out.println("Jogador 1 conectado!");

            System.out.println("Aguardando jogador 2...");
            Socket jogador2 = servidor.accept();
            System.out.println("Jogador 2 conectado!");

            BufferedReader entrada1 =
                    new BufferedReader(new InputStreamReader(jogador1.getInputStream()));

            BufferedReader entrada2 =
                    new BufferedReader(new InputStreamReader(jogador2.getInputStream()));

            PrintWriter saida1 =
                    new PrintWriter(jogador1.getOutputStream(), true);

            PrintWriter saida2 =
                    new PrintWriter(jogador2.getOutputStream(), true);

            saida1.println("Digite PEDRA, PAPEL ou TESOURA:");
            saida2.println("Digite PEDRA, PAPEL ou TESOURA:");

            String jogada1 = entrada1.readLine().toUpperCase();
            String jogada2 = entrada2.readLine().toUpperCase();

            String resultado = verificarVencedor(jogada1, jogada2);

            saida1.println(resultado);
            saida2.println(resultado);

            jogador1.close();
            jogador2.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String verificarVencedor(String j1, String j2) {

        if (j1.equals(j2)) {
            return "EMPATE!";
        }

        if (
                (j1.equals("PEDRA") && j2.equals("TESOURA")) ||
                        (j1.equals("PAPEL") && j2.equals("PEDRA")) ||
                        (j1.equals("TESOURA") && j2.equals("PAPEL"))
        ) {
            return "Jogador 1 venceu! (" + j1 + " x " + j2 + ")";
        }

        return "Jogador 2 venceu! (" + j2 + " x " + j1 + ")";
    }
}