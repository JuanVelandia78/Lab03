
package com.mycompany.cronometro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cronometro extends JFrame {
    private JLabel tiempoLabel;
    private JButton iniciarDetenerBtn;
    private Timer timer;
    private int segundos = 0;

    public Cronometro() {
        setTitle("Cronómetro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        tiempoLabel = new JLabel("00:00:00");
        tiempoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(tiempoLabel);

        iniciarDetenerBtn = new JButton("Iniciar");
        iniciarDetenerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer == null) {
                    iniciarCronometro();
                    iniciarDetenerBtn.setText("Detener");
                } else {
                    detenerCronometro();
                    iniciarDetenerBtn.setText("Iniciar");
                }
            }
        });
        add(iniciarDetenerBtn);
    }

    private void iniciarCronometro() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                segundos++;
                actualizarTiempo();
            }
        }, 0, 1000);
    }

    private void detenerCronometro() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void actualizarTiempo() {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        tiempoLabel.setText(String.format("%02d:%02d:%02d", horas, minutos, segs));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Cronometro().setVisible(true);
        });
    }
}