
package com.ucp.ia;

import com.ucp.cookwithease.model.*;
import com.ucp.recipecleaner.AITools;


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

     TextAnalysis(LinkedList<String> ingredients,LinkedList<Recipe> recipes,LinkedList<String> TitleList){
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
LinkedList<Entry> Analyse(LinkedList<Recipe> recipes){

    /*Initialise Weight on Title */

    for(Entry en : entry) {
         /*Search title for Initialisation*/
        int test=0;
        for(int indextitle=0 ; indextitle < en.getDatatitle().size() ; indextitle ++) {
            if(AITools.contains(en.getRecipeName(),en.getDatatitle().get(indextitle).getName()))
                en.getDatatitle().get(indextitle).setWeight(1);
            else
                test++;
        }

    }
    /*Initialise Weight on Ingredients*/
    for(int index=0 ; index < recipes.size() ; index ++) {
        for (Ingredient ing : recipes.get(index).getIngredients()) {
            for(int index2=0 ; index2 < entry.get(index).getData().size() ; index2++){
                if(entry.get(index).getData().get(index2).getName().equals(ing.getName()))
                    entry.get(index).getData().get(index2).setWeight(1);
            }
        }
    }
    return entry;
}

}
