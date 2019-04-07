package Ia;

import java.util.LinkedList;
import com.ucp.scrapper.Data.*;
import com.ucp.scrapper.database.Ingredient;
import com.ucp.scrapper.database.Recipe;

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
    private static double epsilon = 0.5;
    private static double ALPHA = 0.5;
    private static double BETA = 0.5;
    private static double NEURONSIZE = 20;
    public Kohonen(LinkedList<Ingredient> ingredients, LinkedList<Recipe> recipes) {
        this.recipes = recipes;
        cluster = new LinkedList<Categorie>();
        kohonen = new LinkedList<Neuron>();
        TextAnalysis analysis=new TextAnalysis(Entry);
        Neuron neuron = new Neuron();
        Categorie categorie=new Categorie();
        for(int index =0 ; index < NEURONSIZE ; index++ ){
            kohonen.add(neuron);
            cluster.add(categorie);
        }
        InitWeight(ingredients);
        Entry=analysis.Analyse(recipes);
    }


    /**
     * initialisation of Weight
     * @version 1.0.0.0 : initialisation random
     */
    public void InitWeight(LinkedList<Ingredient> ingredients){
        int index=0;
        //initialisation weight for each Neuron create
        for(Neuron neuron : kohonen) {
            for(int index2=0;index2<ingredients.size() ;index2++) {
                double val= random();
                neuron.getWeight().add(val);
            }
        }
    }

    double nu(int value) {
        if(value > 0 && abs(value) <= dvp){
            return 1-(ALPHA*value) ;
        }
        else if(abs(value) == dvn){
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
            if(kohonen.get(index).getaction() > BestActivity)
                BestActivity=kohonen.get(index).getaction();
                winner = index;
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
        }
    }

    /**
     * modification of the weight
     */
    public void Learning(int winner){
        for(int index =0;index < kohonen.size() ; index++){
            for(int index2=0;index2 < kohonen.get(index).getWeight().size() ; index2++){
                    Double learning= (epsilon*Entry.get(index).getData().get(index2).getWeight()*1.0)*nu(winner-index);
                    kohonen.get(index).getWeight().set(index2,kohonen.get(index).getWeight().get(index)+learning);
            }
        }


    }

    /**
     * Create Categorie after the learning
     */
    public void Clustering(){
        int winner=0;
        for(int index=0; index < Entry.size() ; index ++ ){
            Action(Entry.get(index));
            winner = WinnerDetermined();
            Learning(winner);
            cluster.get(winner).getRecipes().add(recipes.get(index));
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
