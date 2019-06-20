
package com.ucp.ihm;

import com.ucp.xml.exist.query.QuerySimpleUser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PanelsPieChart extends JPanel {


    public PanelsPieChart(Integer idUser, String type) {
        JPanel pnl = new JPanel(new BorderLayout());
        setSize(600, 600);

        DefaultPieDataset dataset = new DefaultPieDataset();
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        ArrayList<Double> allCat = querySimpleUser.getAllProbByCatByType(idUser, type);
        for (int index = 0; index < allCat.size(); index++) {
            if (allCat.get(index) > 0.005) {
                dataset.setValue("Cat " + index, allCat.get(index));
            }

        }

        JFreeChart barChart = ChartFactory.createPieChart("Type : " + type, dataset, true, true, false);
        ChartPanel cPanel = new ChartPanel(barChart);
        pnl.add(cPanel);
        this.add(pnl);
    }
}
