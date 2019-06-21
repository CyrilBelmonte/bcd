package com.ucp.ihm;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private int idUser;
    public Window(int idUser) {

        this.idUser=idUser;
        this.setTitle("Monitoring User " + idUser);

        this.setLocationRelativeTo(null);

        JPanel graphs = new JPanel(new GridLayout(0, 2));

        JButton close = new JButton("Refresh");
        close.addActionListener(this);


        JPanel panel = new PanelUser(idUser);
        panel.add(close);

        graphs.add(new PanelsPieChart(idUser, "starter"));
        graphs.add(new PanelsPieChart(idUser, "main_course"));
        graphs.add(new PanelsPieChart(idUser, "dessert"));
        graphs.add(panel);

        this.setContentPane(graphs);

        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.getContentPane().removeAll();
        JPanel graphs = new JPanel(new GridLayout(0, 2));

        JButton close = new JButton("Refresh");
        close.addActionListener(this);


        JPanel panel = new PanelUser(idUser);
        panel.add(close);

        graphs.add(new PanelsPieChart(idUser, "starter"));
        graphs.add(new PanelsPieChart(idUser, "main_course"));
        graphs.add(new PanelsPieChart(idUser, "dessert"));
        graphs.add(panel);
        this.setContentPane(graphs);
        this.setVisible(true);
    }
}

