
package com.mycompany.cronometro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimacionImagenes extends JFrame {
    private JLabel imagenLabel;
    private JSlider velocidadSlider;
    private Timer timer;
    private ImageIcon[] imagenes;
    private int indiceActual = 0;

    public AnimacionImagenes() {
        setTitle("Animación de Imágenes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imagenes = new ImageIcon[5];
        for (int i = 0; i < 5; i++) {
            imagenes[i] = new ImageIcon("imagen" + (i + 1) + ".png");
        }

        imagenLabel = new JLabel(imagenes[0]);
        add(imagenLabel, BorderLayout.CENTER);

        velocidadSlider = new JSlider(JSlider.HORIZONTAL, 100, 2000, 1000);
        velocidadSlider.addChangeListener(e -> actualizarVelocidad());
        add(velocidadSlider, BorderLayout.SOUTH);

        iniciarAnimacion();
    }

    private void iniciarAnimacion() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indiceActual = (indiceActual + 1) % imagenes.length;
                imagenLabel.setIcon(imagenes[indiceActual]);
            }
        });
        timer.start();
    }

    private void actualizarVelocidad() {
        if (timer != null) {
            timer.setDelay(velocidadSlider.getValue());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnimacionImagenes().setVisible(true);
        });
    }
}
