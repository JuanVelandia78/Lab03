
package com.mycompany.cronometro;

import javax.swing.*;
import java.awt.*;

public class Cronometro extends JFrame {
    private JLabel tiempoLabel;
    private JButton iniciarDetenerBtn;

    public Cronometro() {
        setTitle("Cronómetro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        tiempoLabel = new JLabel("00:00:00");
        tiempoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(tiempoLabel);

        iniciarDetenerBtn = new JButton("Iniciar");
        add(iniciarDetenerBtn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Cronometro().setVisible(true);
        });
    }
}