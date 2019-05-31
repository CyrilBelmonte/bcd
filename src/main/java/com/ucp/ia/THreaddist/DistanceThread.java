package com.ucp.ia.THreaddist;

import com.ucp.ia.Entry;
import com.ucp.ia.Neuron;

import static java.lang.Math.abs;

public class DistanceThread extends  Thread  {
    private Neuron neuron;
    private Entry en;
    private double distance = 0;
    public DistanceThread(Neuron neuron, Entry en) {
        this.neuron=neuron;
        this.en=en;
    }

    public void run(){
        for (int index = 0; index < en.getData().size(); index++) {
            double w1 = en.getData().get(index).getWeight();
            double w2 =neuron.getWeight().get(index);
            double dist=w1-w2;
            distance= distance + abs(dist);
        }
    }



    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
