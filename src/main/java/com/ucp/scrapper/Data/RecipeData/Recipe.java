package com.ucp.scrapper.Data.RecipeData;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    private int recipeTime;
    private int cookingTime;
    private int preparationTime;
    private int numberPersons;
    private int mark;

    private String economicLevel;
    private String difficultyLevel;
    private String title;
    private String picture;

    private List<Ingredient> ingredients;
    private List<WebComment> webComments;
    private List<Step> steps;

    public static class RecipeBuilder {
        public RecipeBuilder setIngredientBuilder(Ingredient... ingredients) {
            this.ingredients = new ArrayList<Ingredient>();
            for (Ingredient ingredient : ingredients) {
                this.ingredients.add(ingredient);
            }
            return this;
        }

        public RecipeBuilder setCommentBuilder(WebComment... webComments) {
            this.webComments = new ArrayList<WebComment>();
            for (WebComment webComment : webComments) {
                this.webComments.add(webComment);
            }
            return this;
        }

        public RecipeBuilder setStepBuilder(Step... steps) {
            this.steps = new ArrayList<Step>();
            for (Step step : steps) {
                this.steps.add(step);
            }
            return this;
        }

    }
}
