package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter saida =
                    new PrintWriter(socket.getOutputStream(), true);

            Scanner teclado = new Scanner(System.in);

            System.out.println(entrada.readLine());

            String jogada = teclado.nextLine();

            saida.println(jogada);

            String resultado = entrada.readLine();

            System.out.println(resultado);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
