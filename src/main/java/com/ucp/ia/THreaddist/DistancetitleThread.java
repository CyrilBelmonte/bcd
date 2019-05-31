package com.ucp.ia.THreaddist;

import com.ucp.ia.Entry;
import com.ucp.ia.Neuron;

import static java.lang.Math.abs;

public class DistancetitleThread extends  Thread  {
    private Neuron neuron;
    private Entry en;
    private double distance = 0;
    public DistancetitleThread(Neuron neuron, Entry en) {
        this.en=en;
        this.neuron=neuron;
    }

    public void run(){
        for (int index2 = 0; index2 < en.getDatatitle().size(); index2++) {
            double w1 = en.getDatatitle().get(index2).getWeight() ;
            double w2 = neuron.getWeighttitle().get(index2);
            double dist=w1-w2;
            distance+=abs(dist);
        }
    }



    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

