package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClienteGUI extends JFrame {

    private JTextArea area;
    private PrintWriter out;
    private BufferedReader in;

    private JButton pedra, papel, tesoura;

    private boolean esperandoResultado = false;

    public ClienteGUI() {

        setTitle("Pedra Papel Tesoura");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(30,30,30));
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("JOGO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.ORANGE);

        area = new JTextArea();
        area.setEditable(false);
        area.setBackground(new Color(45,45,45));
        area.setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(area);

        pedra = botao("PEDRA");
        papel = botao("PAPEL");
        tesoura = botao("TESOURA");

        JPanel botoes = new JPanel();
        botoes.setBackground(new Color(30,30,30));

        botoes.add(pedra);
        botoes.add(papel);
        botoes.add(tesoura);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        conectar();

        pedra.addActionListener(e -> jogar("PEDRA"));
        papel.addActionListener(e -> jogar("PAPEL"));
        tesoura.addActionListener(e -> jogar("TESOURA"));

        setVisible(true);
    }

    private JButton botao(String t) {
        JButton b = new JButton(t);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setPreferredSize(new Dimension(100,40));
        return b;
    }

    private void conectar() {

        try {
            Socket s = new Socket("localhost", 5000);

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            area.append("Conectado ao servidor!\n");

            new Thread(() -> {

                try {

                    String msg;

                    while ((msg = in.readLine()) != null) {

                        area.append(msg + "\n");

                        if (msg.startsWith("RESULTADO:")) {
                            esperandoResultado = false;
                            liberarBotoes(true);
                        }
                    }

                } catch (Exception e) {
                    area.append("Desconectado\n");
                }

            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar");
        }
    }

    private void jogar(String jogada) {

        if (esperandoResultado) return;

        esperandoResultado = true;

        liberarBotoes(false);

        out.println(jogada);

        area.append("Você jogou: " + jogada + "\n");
    }

    private void liberarBotoes(boolean ativo) {

        pedra.setEnabled(ativo);
        papel.setEnabled(ativo);
        tesoura.setEnabled(ativo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteGUI::new);
    }
}
