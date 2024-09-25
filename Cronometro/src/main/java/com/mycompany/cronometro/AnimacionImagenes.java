
package com.mycompany.cronometro;

import javax.swing.*;
import java.awt.*;

public class AnimacionImagenes extends JFrame {
    private JLabel imagenLabel;
    private JSlider velocidadSlider;

    public AnimacionImagenes() {
        setTitle("Animación de Imágenes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imagenLabel = new JLabel(new ImageIcon("imagen1.png"));
        add(imagenLabel, BorderLayout.CENTER);

        velocidadSlider = new JSlider(JSlider.HORIZONTAL, 100, 2000, 1000);
        add(velocidadSlider, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnimacionImagenes().setVisible(true);
        });
    }
}
