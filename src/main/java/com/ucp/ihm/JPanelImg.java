package com.ucp.ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class JPanelImg extends JPanel {

    private BufferedImage image;

    public JPanelImg() {
        try {
            image = ImageIO.read(new File("./logo.png"));

        } catch (Exception e) {
            e.printStackTrace();
            this.add(new Label("Image not found"));
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
