package com.ucp.scrapper.Data;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class Recipe {
    private int recipeTime;
    private int cookingTime;
    private int preparationTime;
    private int quantities;


    private String economicLevel;
    private String difficultyLevel;

    private List<Ingredients> ingredients;

    private List<WebComments> webComments;

    private Steps steps;

    public static class RecipeBuilder {
        public void setIngredientsBuider(Ingredients... ingredients) {
            for (Ingredients ingredient : ingredients) {
                this.ingredients.add(ingredient);
            }
        }

        public void setWebCommentsBuilder(WebComments... webComments) {
            for (WebComments webComment : webComments) {
                this.webComments.add(webComment);
            }
        }
    }
}
