
package com.ucp.ihm;
        import java.awt.*;
        import java.util.ArrayList;
        import javax.swing.*;

        import com.ucp.xml.exist.query.QuerySimpleUser;
        import org.jfree.chart.*;
        import org.jfree.data.general.DefaultPieDataset;


public class Panels2 extends JPanel {



    public Panels2(Integer idUser,String type) {
        JPanel pnl = new JPanel(new BorderLayout());
        setSize(600, 600);

        DefaultPieDataset dataset = new DefaultPieDataset( );
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        ArrayList<Double> test = querySimpleUser.getAllProbByCatByType(idUser, type);
        System.out.println(test.size());
        for (int index = 0;index < test.size();index++){
            if(test.get(index)>0.005){
                dataset.setValue("Cat "+index,test.get(index));
            }

        }

        JFreeChart barChart  = ChartFactory.createPieChart("Type : "+type,dataset,false, false, false);
        ChartPanel cPanel = new ChartPanel(barChart);
        pnl.add(cPanel);
        this.add(pnl);
    }
}
