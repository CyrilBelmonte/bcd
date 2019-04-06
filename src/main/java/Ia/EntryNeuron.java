package Ia;

import java.util.LinkedList;

public class EntryNeuron {
    LinkedList<IngredientsWeight> data;
    String RecipeName;

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public EntryNeuron(LinkedList<IngredientsWeight> data) {
        this.data = data;
    }

    public LinkedList<IngredientsWeight> getData() {
        return data;
    }

    public void setData(LinkedList<IngredientsWeight> data) {
        this.data = data;
    }
}
