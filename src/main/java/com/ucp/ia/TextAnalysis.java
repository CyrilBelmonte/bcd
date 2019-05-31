
package com.ucp.ia;

import com.ucp.ai_experiments.Constants;
import com.ucp.cookwithease.model.*;
import com.ucp.cleaners.AITools;


import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Archambault Vincent
 * @version 1.0.0.0;
 *
 * Recipes text  analysis
 */


public class TextAnalysis {
    private LinkedList<Entry> entry = new LinkedList<>();
    private LinkedList<IngredientsWeight> titleweight = new LinkedList<>();
    private LinkedList<IngredientsWeight> MaxIngredients = new LinkedList<>();
    String type = "";
    public TextAnalysis(LinkedList<String> ingredients,LinkedList<Recipe> recipes,LinkedList<String> TitleList){
         this.type=type;
         for (String ing : ingredients) {
             if(!ing.equals("poivre") && !ing.equals("sel")) {
                 IngredientsWeight iw2 = new IngredientsWeight(ing);
                 MaxIngredients.add(iw2);
             }
         }
        for(int index=0; index < recipes.size() ;index++) {
            LinkedList<IngredientsWeight> iwlist = new LinkedList<>();
            LinkedList<IngredientsWeight> twlist = new LinkedList<>();
            /*Create Weight Based on Title */
            for (String title : TitleList) {
                    IngredientsWeight tw = new IngredientsWeight(title);
                    twlist.add(tw);

            }
            /*Create Ingredient weight*/
            for (String ing : ingredients) {
                if(!ing.equals("poivre") && !ing.equals("sel")) {
                    IngredientsWeight iw = new IngredientsWeight(ing);

                    iwlist.add(iw);

                }
            }
            /*
            for(Ingredient ingredient : recipes.get(index).getIngredients() ){
                double quantities = ingredient.getQuantity()/recipes.get(index).getPersons();
                quantities=AITools.normalizeQuantity(quantities,ingredient.getUnit());
                for(int index2=0 ; index2 < MaxIngredients.size() ; index2++) {
                    if (MaxIngredients.get(index2).getName().equals(ingredient.getCleanedName())) {
                        if (MaxIngredients.get(index2).getWeight() < quantities)
                            MaxIngredients.get(index2).setWeight(quantities);
                    }
                }
            }
            */
            Entry en = new Entry(iwlist,twlist, recipes.get(index).getName());
            entry.add(en);
        }


    }


/**
     * Function
     * @version 1.0.0.0 : count number of time you see the Ingredients
     * @param recipes : list of Recipes
     * @return all Recipes with each
     */


/*
    LinkedList<Entry> Analyse(LinkedList<Recipe> recipes){
        for(int index=0 ; index < recipes.size() ; index ++){
            String text="";


            for(int index2=0;index2 < recipes.get(index).getSteps().size();index2++) {
                text += recipes.get(index).getSteps().get(index2).getDescription();
            }
            String textsplit[] = text.split(" ");
            int count = 0;
            int counttotal=0;


            for(int index3 = 0; index3 < entry.get(index).getData().size() ;index3++ ) {
                for (int index4 = 0; index4 < textsplit.length; index4++) {
                    if (entry.get(index).getData().get(index3).getName().equals(textsplit[index4]))
                        count++;
                }
                counttotal+=count;
                entry.get(index).getData().get(index3).setWeight(count);
            }
            for(int index5=0;index5 <  entry.get(index).getData().size() ; index5++ )
                entry.get(index).getData().get(index5).setWeight( entry.get(index).getData().get(index5).getWeight()/counttotal);
        }
        return entry;
    }
*/
/**
 *  Function
     * V 1.0.0.1 : binary Weight 0 the ingredients isn't exist
 * Add Weight based on Title
     * @param recipes : list of Recipes
     * @return all Recipes with each
            */
public LinkedList<Entry> Analyse(LinkedList<Recipe> recipes){
    /*Initialise Weight on Title */
    HashMap<String, Double> maxIngredientsQuantities = Constants.getMaxIngredientsQuantities();
    for(Entry en : entry) {
         /*Search title for Initialisation*/
        int test=0;
        for(int indextitle=0 ; indextitle < en.getDatatitle().size() ; indextitle ++) {
            if(AITools.contains(en.getRecipeName(),en.getDatatitle().get(indextitle).getName()))
                en.getDatatitle().get(indextitle).setWeight(1.0);
            else
                test++;
        }

    }
    /*Initialise Weight on Ingredients*/
    for(int index=0 ; index < recipes.size() ; index ++) {
        for (Ingredient ing : recipes.get(index).getIngredients()) {
            for(int index2=0 ; index2 < entry.get(index).getData().size() ; index2++){
                if(entry.get(index).getData().get(index2).getName().equals(ing.getCleanedName())) {
                    double quantities = ing.getQuantity()/recipes.get(index).getPersons();
                    quantities=AITools.normalizeQuantity(quantities,ing.getUnit());
                    boolean validunit = AITools.isUnitValid(ing.getUnit());
                    if(validunit){
                        double weight=0;
                         weight = quantities/(maxIngredientsQuantities.get(ing.getCleanedName())+1);
                        entry.get(index).getData().get(index2).setWeight(weight);
                    }
                    else {
                        double weight=0;
                        weight  = quantities/(maxIngredientsQuantities.get(ing.getCleanedName())+1);

                        entry.get(index).getData().get(index2).setWeight(weight);
                    }

                }
            }
        }
    }
    /*
    try {
        String disp = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/java/com/ucp/ia/csv/Entry_Ingredient_"+type+".csv"));
        for (int index5 = 0; index5 < entry.size(); index5++) {
            disp=disp+entry.get(index5).getRecipeName()+";";
            for (int index6 = 0; index6 < entry.get(0).getData().size(); index6++) {
                disp = disp + entry.get(index5).getData().get(index6).getName() + ";"+ entry.get(index5).getData().get(index6).getWeight() + ";";
            }
            disp = disp + "\n";
        }
        writer.write(disp);
        writer.close();
    }catch (IOException e){

    }

    try {
        String disp = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/java/com/ucp/ia/csv/Entry_Title_"+type+".csv"));
        for (int index5 = 0; index5 < entry.size(); index5++) {
            disp=disp+entry.get(index5).getRecipeName()+";";
            for (int index6 = 0; index6 < entry.get(0).getDatatitle().size(); index6++) {
                disp = disp + entry.get(index5).getDatatitle().get(index6).getName() + ";"+ entry.get(index5).getDatatitle().get(index6).getWeight() + ";";
            }
            disp = disp + "\n";
        }
        writer.write(disp);
        writer.close();
    }catch (IOException e){

    }
    */

    return entry;
}

}
