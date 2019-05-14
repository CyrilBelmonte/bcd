
package com.ucp.ia;

import com.ucp.cookwithease.model.*;


import java.util.LinkedList;

/**
 * @author Archambault Vincent
 * @version 1.0.0.0;
 *
 * Recipes text  analysis
 */

/*
* TEST initialiation poid  0 ou  1 pour chaque ingredients
*
* */
public class TextAnalysis {

    LinkedList<EntryNeuron> entry = new LinkedList<>();
    TextAnalysis(LinkedList<String> ingredients,LinkedList<Recipe> recipes){

        for(int index=0; index < recipes.size() ;index++) {
            LinkedList<IngredientsWeight> iwlist = new LinkedList<>();
            for (String ing : ingredients) {
                IngredientsWeight iw = new IngredientsWeight(ing);
                iwlist.add(iw);
            }
            EntryNeuron en = new EntryNeuron(iwlist);
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
    LinkedList<EntryNeuron> Analyse(LinkedList<Recipe> recipes){
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

LinkedList<EntryNeuron> Analyse(LinkedList<Recipe> recipes){
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
