package com.ucp.ihm;

import com.ucp.xml.exist.query.QuerySimpleUser;
import org.jfree.ui.RefineryUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Ihm extends JFrame implements ActionListener {

    private Object item;

    public Ihm() {
        this.setTitle("Monitoring Users ");

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(firstFrame());

        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private JPanel firstFrame() {
        JLabel label = new JLabel();
        try {
            BufferedImage myPicture = ImageIO.read(new File("src/main/resources/logo.png"));
            ImageIcon image = new ImageIcon(myPicture);
            label.setIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
            label.setText("image not found");

        }

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        QuerySimpleUser querySimpleUser = new QuerySimpleUser();

        ArrayList<Integer> users = querySimpleUser.getUserList();

        int size = users.size();

        Object[] elements = new Object[size];

        for (int index = 0; index < size; index++) {
            elements[index] = users.get(index);
        }

        JComboBox jComboBox = new JComboBox(elements);

        JButton jButton = new JButton("find");

        jPanel.add(label,BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        panel2.add(new Label("Select user "));
        panel2.add(jComboBox);
        panel2.add(jButton);
        jPanel.add(panel2,BorderLayout.AFTER_LINE_ENDS);

        this.item = jComboBox.getSelectedItem();
        jButton.addActionListener(this);
        return jPanel;
    }

    public void actionPerformed(ActionEvent arg0) {
        Window window = new Window((int) this.item);
    }

    public static void main(String[] args) {
        Ihm ihm = new Ihm();

    }
}
