
package com.mycompany.cronometro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cronometro extends JFrame {
    private JLabel tiempoLabel;
    private JButton iniciarDetenerBtn;
    private JButton reiniciarBtn;
    private JTextField alarmaMinutos;
    private JButton configurarAlarmaBtn;
    private Timer timer;
    private int segundos = 0;
    private int alarmaSegundos = -1;
    private boolean alarmaActivada = false;

    public Cronometro() {
        setTitle("Cronómetro con Alarma");
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

        reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarCronometro();
            }
        });
        add(reiniciarBtn);

        alarmaMinutos = new JTextField(5);
        add(new JLabel("Minutos para alarma:"));
        add(alarmaMinutos);

        configurarAlarmaBtn = new JButton("Configurar Alarma");
        configurarAlarmaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                configurarAlarma();
            }
        });
        add(configurarAlarmaBtn);
    }

    private void iniciarCronometro() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                segundos++;
                actualizarTiempo();
                verificarAlarma();
            }
        }, 0, 1000);
    }

    private void detenerCronometro() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void reiniciarCronometro() {
        detenerCronometro();
        segundos = 0;
        alarmaActivada = false;
        actualizarTiempo();
        iniciarDetenerBtn.setText("Iniciar");
    }

    private void actualizarTiempo() {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        tiempoLabel.setText(String.format("%02d:%02d:%02d", horas, minutos, segs));
    }

    private void configurarAlarma() {
        try {
            int minutos = Integer.parseInt(alarmaMinutos.getText());
            alarmaSegundos = minutos * 60;
            JOptionPane.showMessageDialog(this, "Alarma configurada para " + minutos + " minutos.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de minutos.");
        }
    }

    private void verificarAlarma() {
        if (alarmaSegundos != -1 && segundos >= alarmaSegundos && !alarmaActivada) {
            alarmaActivada = true;
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Toolkit.getDefaultToolkit().beep();
                }
            }, 0, 10000); // Suena cada 10 segundos
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cronometro().setVisible(true);
            }
        });
    }
}