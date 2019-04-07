package Ia;

import com.ucp.scrapper.database.Recipe;

import java.util.LinkedList;
/**
 * @author Archambault Vincent
 * @version 1.0.0.0;
 *
 * Recipes text  analysis
 */
public class TextAnalysis {

    LinkedList<EntryNeuron> entry;
    TextAnalysis(LinkedList<EntryNeuron> entry){
        this.entry=entry;
    }

    /**
     * Function
     * @version 1.0.0.0 : count number of time you see the Ingredients
     * @param recipes : list of Recipes
     * @return all Recipes with each
     */
    LinkedList<EntryNeuron> Analyse(LinkedList<Recipe> recipes){
        for(int index=0 ; index < recipes.size() ; index ++){
            String text="";
            /*group all step in one text*/
            for(int index2=0;index2 < recipes.get(index).SizeSteps();index2++) {
                text += recipes.get(index).getSteps(index2).getInstructions();
            }
            String textsplit[] = text.split(" ");
            int count = 0;
            int counttotal=0;
            /*count the number of  */
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
}
