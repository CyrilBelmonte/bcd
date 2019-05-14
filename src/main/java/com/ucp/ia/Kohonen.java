
package com.ucp.ia;

import com.ucp.cookwithease.model.*;

import java.util.LinkedList;


import static java.lang.Math.*;


/**
 * @author Archambault Vincent
 * @version 1.0.0.0;
 *
 * Kohonen card code
 */

public class Kohonen {
    private LinkedList<Neuron> kohonen;
    private LinkedList<EntryNeuron> Entry;
    private LinkedList<Categorie> cluster ;
    private LinkedList<Recipe> recipes;
    private static int  dvp=7;
    private static int  dvn=9;
    private static double epsilon = 0.9;
    private static double ALPHA = 0.125;
    private static double BETA = 0.125;
    private static double NEURONSIZE = 50;
    private static int LEARNINGSIZE = 50;
    public Kohonen(LinkedList<String> ingredients, LinkedList<Recipe> recipes) {
        this.recipes = recipes;
        cluster = new LinkedList<>();
        kohonen = new LinkedList<>();
        Entry = new LinkedList<>();
        TextAnalysis analysis=new TextAnalysis(ingredients,recipes);



        InitWeight(ingredients);
        Entry=analysis.Analyse(recipes);
    }



/**
     * initialisation of Weight
     * @version 1.0.0.0 : initialisation random
     */

    public void InitWeight(LinkedList<String> ingredients){
        for(int index =0 ; index < NEURONSIZE ; index++ ){
            Neuron neuron = new Neuron();
            Categorie categorie=new Categorie();
            for(String ing :ingredients) {
                double val= random();
                neuron.getWeight().add(val);
            }
            kohonen.add(neuron);
            cluster.add(categorie);
        }

    }



    double nu(int value) {
        if(value > 0 && abs(value) <= dvp){
            return 1-(ALPHA*value) ;
        }
        else if(abs(value) > dvp && abs(value) <= dvn){
            return -BETA*(value-dvp);
        }
        else if(value == 0) {
            return 1;
        }
        else{
            return 0;
        }
    }
/**
     *
     */

    public int WinnerDetermined(){
        int winner = 0;
        double BestActivity=0;
        for(int index=0 ; index<kohonen.size() ;index++ ) {
            if(kohonen.get(index).getaction() > BestActivity) {
                BestActivity = kohonen.get(index).getaction();
                winner = index;
            }
        }
        return winner;
    }

    public void Action(EntryNeuron en){
        double distance=0;
        for(Neuron neuron : kohonen) {
            for (int index = 0; index < en.getData().size(); index++) {
                distance+= pow(en.getData().get(index).getWeight()-neuron.getWeight().get(index),2);
            }
            distance = sqrt(distance);
            neuron.setPotential(distance);
            neuron.setaction((1.0/(1+distance)));
        }
    }


/**
     * modification of the weight
     */

    public void Learning(int winner,int entry){
        for(int index =0;index < kohonen.size() ; index++){
            for(int index2=0;index2 < kohonen.get(index).getWeight().size() ; index2++){
                    Double learning= (epsilon*(Entry.get(entry).getData().get(index2).getWeight()*1.0- kohonen.get(index).getWeight().get(index2))*nu(winner-index));
                    kohonen.get(index).getWeight().set(index2,kohonen.get(index).getWeight().get(index2)+learning);
                    if( kohonen.get(index).getWeight().get(index2) < 0)
                        kohonen.get(index).getWeight().set(index2,0.0);
                if( kohonen.get(index).getWeight().get(index2) > 1)
                    kohonen.get(index).getWeight().set(index2,1.0);


            }
        }


    }


/**
     * Create Categorie after the learning
     */

    public void Clustering(){
        int winner=0;



        for(int indextest=0; indextest < LEARNINGSIZE ; indextest++) {
            for (int index = 0; index < Entry.size(); index++) {
                Action(Entry.get(index));
                winner = WinnerDetermined();
                Learning(winner, index);
            }
         //   System.out.println("APPRENTISSAGE NB "+(indextest+1));
        }

        for(int index3=0; index3 < Entry.size() ; index3 ++ ){
            Action(Entry.get(index3));
            winner = WinnerDetermined();
            cluster.get(winner).getRecipes().add(recipes.get(index3));
        }

    }

    public LinkedList<Neuron> getKohonen() {
        return kohonen;
    }

    public void setKohonen(LinkedList<Neuron> kohonen) {
        this.kohonen = kohonen;
    }

    public LinkedList<EntryNeuron> getEntry() {
        return Entry;
    }

    public void setEntry(LinkedList<EntryNeuron> entry) {
        Entry = entry;
    }

    public LinkedList<Categorie> getCluster() {
        return cluster;
    }

    public void setCluster(LinkedList<Categorie> cluster) {
        this.cluster = cluster;
    }
}

