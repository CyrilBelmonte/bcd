package com.ucp.cookwithease.model;

import com.ucp.cookwithease.tools.Tools;
import lombok.*;

import java.util.LinkedList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    // Used by the database
    @Setter(AccessLevel.NONE)
    private int id;

    private String name;
    private int duration;
    private int persons;
    private DishType type;
    private Level cost;
    private Level difficulty;
    private float rating;
    private String picture;

    private LinkedList<Ingredient> ingredients = new LinkedList<>();
    private LinkedList<Step> steps = new LinkedList<>();
    private LinkedList<Comment> comments = new LinkedList<>();

    public void setId(int id) {
        this.id = id;

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipeID(id);
        }

        for (Step step : steps) {
            step.setRecipeID(id);
        }

        for (Comment comment : comments) {
            comment.setRecipeID(id);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setRecipeID(id);
        ingredients.addLast(ingredient);
    }

    public void addStep(Step step) {
        step.setRecipeID(id);
        steps.addLast(step);
    }

    public void addComment(Comment comment) {
        comment.setRecipeID(id);
        comments.addFirst(comment);
    }

    public boolean hasIngredient(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return true;
            }
        }

        return false;
    }

    public Ingredient getIngredientFromName(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getCleanedName().equals(ingredientName)) {
                return ingredient;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuffer ingredientsDescription = new StringBuffer();
        StringBuffer stepsDescription = new StringBuffer();

        for (Ingredient ingredient : ingredients) {
            ingredientsDescription.append(ingredient + "\n");
        }

        for (Step step : steps) {
            stepsDescription.append(step + "\n");
        }

        return name + "\n" +
            Tools.repeat("-", name.length()) + "\n" +
            "  Duration: " + duration + " min | Persons: " + persons +
            " | Cost: " + cost + " | Difficulty: " + difficulty + "\n" +
            "  Rating: " + rating + " stars\n" +
            "  Picture: " + picture + "\n\n" +
            "  Ingredients:\n" + ingredientsDescription + "\n" +
            "  Steps:\n" + stepsDescription;
    }
}
