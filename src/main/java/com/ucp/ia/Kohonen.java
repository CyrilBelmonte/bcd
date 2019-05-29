
package com.ucp.ia;

import com.ucp.cookwithease.model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private LinkedList<com.ucp.ia.Entry> Entry;
    private LinkedList<Categorie> cluster ;
    private LinkedList<Recipe> recipes;
    private LinkedList<String> TitleList;
    private static int  dvp=7;
        private static int  dvn=9;
        private static double epsilon = 0.5;
        private static double ALPHA = 0.125;
        private static double BETA = 0.125;
        private static int NEURONSIZE = 100;
        private static int LEARNINGSIZE =2000;
        private LinkedList<Integer> Entrychoosen;

    public Kohonen(LinkedList<String> ingredients, LinkedList<Recipe> recipes, LinkedList<String> TitleList) {
            this.recipes = recipes;
            cluster = new LinkedList<>();
            kohonen = new LinkedList<>();
        Entry = new LinkedList<>();
        this.TitleList=TitleList;
        TextAnalysis analysis=new TextAnalysis(ingredients,recipes,TitleList);
        Entry=analysis.Analyse(recipes);
        Entrychoosen= new LinkedList<>();
        InitWeight(ingredients);
    }

    private int pickEntry() {
        if (Entrychoosen.size() == 0) {
            for (int i = 0; i < Entry.size(); i++) {
                Entrychoosen.addLast(i);
            }
        }

        int pickedIndex = (int) (Math.random() * Entrychoosen.size());
        Entrychoosen.remove(pickedIndex);

        return pickedIndex;
    }



/**
     * initialisation of Weight
     * @version 1.0.0.0 : initialisation random
     */
    public void InitWeight(LinkedList<String> ingredients){


        for(int index =0 ; index < NEURONSIZE ; index++ ){
            Neuron neuron = new Neuron();
            Categorie categorie=new Categorie(NEURONSIZE,index);
            for(String ing :ingredients) {
                double val = random();
                neuron.getWeight().add(val);
            }

            for(String Title : TitleList){
                neuron.getWeighttitle().add(0.0);
            }
            kohonen.add(neuron);
            cluster.add(categorie);
        }




    }



    double nu(int value) {

        if(value <= dvp){
            return 1-(ALPHA*value) ;
        }
        else if(value > dvp && value <= dvn){
            return -BETA*(value-dvp);
        }
        else if(value == 0) {
            return 1;
        }
        else{
            return 0;
        }
    }


/*
Double voisinage(int index){
    Double distance = kohonen.get(index).getPotential();

    if(distance > PERIMETRE_Distant  ){
        return 0.0;
    }

    else {
        double voisin= 1-distance/ PERIMETRE_Distant;
        return voisin;
    }
}
*/

/**
     *
     */

    public int WinnerDetermined(){
        int winner = 0;
        double BestActivity=0;
        for(int index=0 ; index<kohonen.size() ;index++ ) {
            double action=kohonen.get(index).getaction();
            if(action > BestActivity) {
                BestActivity = kohonen.get(index).getaction();
                winner = index;
            }
        }
        //if (winner == 0)
           // System.out.println("STOP");
        return winner;
    }

    public void Action(com.ucp.ia.Entry en){


        for(Neuron neuron : kohonen) {
            double distancetitle=0;
            double distance=0;
            for (int index = 0; index < en.getData().size(); index++) {
                double w1 = en.getData().get(index).getWeight();
                double w2 =neuron.getWeight().get(index);
                double dist=w1-w2;
                distance= distance + pow(dist,2);
            }
            /*V1.0.0.1 add title weight*/

            for (int index2 = 0; index2 < en.getDatatitle().size(); index2++) {
                double w1 = en.getDatatitle().get(index2).getWeight() ;
                double w2 = neuron.getWeighttitle().get(index2);
                double dist=w1-w2;
                distancetitle+= pow((dist),2);
            }


            distance=(distance+distancetitle);
            distance = sqrt(distance);
            neuron.setPotential(distance);
            neuron.setaction((1.0/(1+distance)));
        }
    }


    /**
     * modification of the weight
     */

    public void Learning(int winner,int entry){
        Double learning=0.0;
        Double entryweight=0.0;
        Double neuronweight=0.0;
        for(int index =0;index < kohonen.size() ; index++){
            double voisin = nu(abs(winner - index));//voisinage(index);
            int size = kohonen.get(index).getWeight().size();
            for(int index2=0;index2 < size ; index2++){
                entryweight = Entry.get(entry).getData().get(index2).getWeight();
                neuronweight = kohonen.get(index).getWeight().get(index2);
                if(index == winner){
                    learning = (epsilon * (entryweight - neuronweight));

                }
                else {
                    learning = (epsilon * (entryweight - neuronweight)*voisin /*nu(winner-index)*/);
                }
                    kohonen.get(index).getWeight().set(index2,kohonen.get(index).getWeight().get(index2)+learning);
                    if( kohonen.get(index).getWeight().get(index2) < -1)
                        kohonen.get(index).getWeight().set(index2,-1.0);
                if( kohonen.get(index).getWeight().get(index2) > 1)
                    kohonen.get(index).getWeight().set(index2,1.0);


            }
            int sizetitle = kohonen.get(index).getWeighttitle().size();
            for(int index2=0;index2 < sizetitle ; index2++){
                 entryweight = Entry.get(entry).getDatatitle().get(index2).getWeight();
                 neuronweight = kohonen.get(index).getWeighttitle().get(index2);

                if(index == winner){
                     learning = (epsilon * (entryweight - neuronweight));
                }
                else {
                    learning = (epsilon * (entryweight - neuronweight) * nu(index));
                }
                kohonen.get(index).getWeighttitle().set(index2,kohonen.get(index).getWeighttitle().get(index2)+learning);
                if( kohonen.get(index).getWeighttitle().get(index2) < 0)
                    kohonen.get(index).getWeighttitle().set(index2,0.0);
                if( kohonen.get(index).getWeighttitle().get(index2) > 1)
                    kohonen.get(index).getWeighttitle().set(index2,1.0);

            }
        }

    }


    /**
     * Create Categorie after the learning
     */

    public void Clustering(){

        /*
        try {
            String disp = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/java/com/ucp/ia/csv/Ingredientlist.csv"));
            for (int index5 = 0; index5 < Entry.get(0).getData().size(); index5++) {
                    disp = disp + Entry.get(0).getData().get(index5).getName() + "\n";
            }
            writer.write(disp);
            writer.close();
        }catch (IOException e){

        }
        */
        int winner=0;
        long debut = System.currentTimeMillis();
        for(int indextest=0; indextest < LEARNINGSIZE ; indextest++) {
            if(indextest==1) System.out.println("DUREE MIN:"+(System.currentTimeMillis()-debut)*LEARNINGSIZE/60000);
            int index = pickEntry();
                    Action(Entry.get(index));
                    winner = WinnerDetermined();
                        Learning(winner, index);



            System.out.println("APPRENTISSAGE NB "+(indextest+1));
        }

        /*
        try {
            String disp = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/java/com/ucp/ia/csv/Result.csv"));
            for (int index5 = 0; index5 < NEURONSIZE; index5++) {
                for (int index6 = 0; index6 < kohonen.get(0).getWeight().size(); index6++) {
                disp = disp + kohonen.get(index5).getWeight().get(index6) + ";";
            }
            disp = disp + "\n";
        }
        writer.write(disp);
        writer.close();
        }catch (IOException e){

        }
*/


        for(int index3=0; index3 < Entry.size() ; index3 ++ ){
            Action(Entry.get(index3));
            winner = WinnerDetermined();
            cluster.get(winner).getRecipes().add(recipes.get(index3));
            cluster.get(winner).getDistance().add(kohonen.get(winner).getPotential());
        }

        try {
            String disp2 = "";
            BufferedWriter writer2 = new BufferedWriter(new FileWriter("./src/main/java/com/ucp/ia/csv/Recette"+recipes.get(0).getType()+".csv"));
            for (int index5 = 0; index5 < NEURONSIZE; index5++) {
                 for (int index6 = 0; index6 < cluster.get(index5).getRecipes().size(); index6++) {
                    disp2 = disp2 + cluster.get(index5).getRecipes().get(index6).getName() + ";";
                }
                disp2 = disp2 + "\n";
            }
            writer2.write(disp2);
            writer2.close();
        }catch (IOException e){

        }


    }

    public LinkedList<Neuron> getKohonen() {
        return kohonen;
    }

    public void setKohonen(LinkedList<Neuron> kohonen) {
        this.kohonen = kohonen;
    }

    public LinkedList<com.ucp.ia.Entry> getEntry() {
        return Entry;
    }

    public void setEntry(LinkedList<com.ucp.ia.Entry> entry) {
        Entry = entry;
    }

    public LinkedList<Categorie> getCluster() {
        return cluster;
    }

    public void setCluster(LinkedList<Categorie> cluster) {
        this.cluster = cluster;
    }
}

