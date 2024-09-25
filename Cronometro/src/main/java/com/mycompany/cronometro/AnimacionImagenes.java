
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

        // Cargar las imágenes (reemplaza esto con tus propias imágenes)
        imagenes = new ImageIcon[5];
        for (int i = 0; i < 5; i++) {
            imagenes[i] = new ImageIcon("imagenes/man" + (i + 1) + ".png");
        }

        // Crear y configurar el JLabel para mostrar las imágenes
        imagenLabel = new JLabel(imagenes[0]);
        add(imagenLabel, BorderLayout.CENTER);

        // Crear y configurar el JSlider para controlar la velocidad
        velocidadSlider = new JSlider(JSlider.HORIZONTAL, 100, 2000, 1000);
        velocidadSlider.setMajorTickSpacing(500);
        velocidadSlider.setMinorTickSpacing(100);
        velocidadSlider.setPaintTicks(true);
        velocidadSlider.setPaintLabels(true);
        velocidadSlider.addChangeListener(e -> actualizarVelocidad());
        add(velocidadSlider, BorderLayout.SOUTH);

        // Iniciar la animación
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