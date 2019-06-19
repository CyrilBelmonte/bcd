package com.ucp.ihm;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import com.ucp.xml.exist.query.QuerySimpleUser;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Panels extends JPanel {



    public Panels(Integer idUser,String type) {
        JPanel pnl = new JPanel(new BorderLayout());
        setSize(600, 600);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        ArrayList<Double> test = querySimpleUser.getAllProbByCatByType(idUser, type);
        System.out.println(test.size());
        for (int index = 0;index < test.size();index++){
            if(test.get(index)>0.005){
                dataset.addValue((test.get(index)),type,"Cat "+index);
            }
        }

        JFreeChart barChart  = ChartFactory.createBarChart("Type : "+type,"Categories","Prob", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel cPanel = new ChartPanel(barChart);
        pnl.add(cPanel);
        this.add(pnl);
    }
}
