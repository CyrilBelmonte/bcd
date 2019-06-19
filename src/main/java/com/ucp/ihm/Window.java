package com.ucp.ihm;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        this.setTitle("Monitoring Web Site");

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel(new GridLayout(0, 2));

        jPanel.add(new Panels(5, "starter"));
        jPanel.add(new Panels(5, "main_course"));
        jPanel.add(new Panels(5, "dessert"));

        this.setContentPane(jPanel);

        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }
}

