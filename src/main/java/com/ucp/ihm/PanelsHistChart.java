package com.ucp.ihm;

import com.ucp.xml.exist.query.QuerySimpleUser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelsHistChart extends JPanel {

    public PanelsHistChart(int idUser, String type) {
        JPanel pnl = new JPanel(new BorderLayout());
        setSize(600, 600);

        double[] value = new double[200];
        HistogramDataset dataset = new HistogramDataset();
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        ArrayList<Double> test = querySimpleUser.getAllProbByCatByType(idUser, type);
        for (int index = 0; index < test.size(); index++) {
            value[index] = test.get(index);
        }
        dataset.addSeries("Histogram", value, 200);
        JFreeChart barChart = ChartFactory.createHistogram("Type : " + type, "Categories", "Prob", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel cPanel = new ChartPanel(barChart);
        pnl.add(cPanel);
        this.add(pnl);
    }
}
