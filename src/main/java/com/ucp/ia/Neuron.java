package com.ucp.ia;

import java.util.LinkedList;

/**
 * *
 */
public class Neuron {

    private double action;
    private double potential;
    private LinkedList<Double> Weight;

    public LinkedList<Double> getWeight() {
        return Weight;
    }

    public void setWeight(LinkedList<Double> weight) {
        Weight = weight;
    }

    


    public Neuron() {
        Weight=new LinkedList<Double>();
    }

    public double getaction() {
        return action;
    }

    public void setaction(double action) {
        this.action = action;
    }

    public double getPotential() {
        return potential;
    }

    public void setPotential(double potential) {
        this.potential = potential;
    }

}
