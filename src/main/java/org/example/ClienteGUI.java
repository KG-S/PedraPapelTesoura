package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClienteGUI extends JFrame {

    private JTextArea areaMensagens;
    private PrintWriter saida;
    private BufferedReader entrada;

    public ClienteGUI() {

        setTitle("Pedra Papel Tesoura");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo =
                new JLabel("PEDRA PAPEL TESOURA TCP",
                        SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);

        JButton pedra = new JButton("🪨 PEDRA");
        JButton papel = new JButton("📄 PAPEL");
        JButton tesoura = new JButton("✂️ TESOURA");

        JPanel painelBotoes = new JPanel();

        painelBotoes.add(pedra);
        painelBotoes.add(papel);
        painelBotoes.add(tesoura);

        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(areaMensagens), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        conectarServidor();

        pedra.addActionListener(e -> enviarJogada("PEDRA"));
        papel.addActionListener(e -> enviarJogada("PAPEL"));
        tesoura.addActionListener(e -> enviarJogada("TESOURA"));

        setVisible(true);
    }

    private void conectarServidor() {

        try {

            Socket socket = new Socket("localhost", 5000);

            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            saida = new PrintWriter(
                    socket.getOutputStream(), true);

            areaMensagens.append("Conectado ao servidor.\n");

            new Thread(() -> {

                try {

                    String mensagem;

                    while ((mensagem = entrada.readLine()) != null) {

                        areaMensagens.append(mensagem + "\n");

                        if (mensagem.contains("venceu")
                                || mensagem.contains("EMPATE")) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    mensagem,
                                    "Resultado",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }

                } catch (Exception e) {

                    areaMensagens.append(
                            "\nConexão encerrada.\n");
                }

            }).start();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível conectar ao servidor."
            );
        }
    }

    private void enviarJogada(String jogada) {

        if (saida != null) {

            saida.println(jogada);

            areaMensagens.append(
                    "Você escolheu: " + jogada + "\n");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                ClienteGUI::new
        );
    }
}
