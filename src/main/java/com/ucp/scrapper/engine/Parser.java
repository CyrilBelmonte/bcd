package com.ucp.scrapper.engine;

import com.ucp.scrapper.data.WebConnection.Marmiton;
import com.ucp.scrapper.data.recipedata.Ingredient;
import com.ucp.scrapper.data.recipedata.Recipe;
import com.ucp.scrapper.data.recipedata.Step;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

@Data
public class Parser {
    private Document documentWeb;

    private Recipe recipe;


    private Marmiton marmiton;

    public Parser(String url) {
        try {
            Scrapper scrapper = new Scrapper(url);
            this.marmiton = new Marmiton();

            this.documentWeb = scrapper.getDocumentWeb();

            this.recipe = new Recipe(getRecipeTimeFromWeb(), getCookingTimeFromWeb(), getPreparationTime(), getNumberPersonFromWeb(), getMarkFromWeb(), getEconomicLevelFromWeb(), getDifficultyLevelFromWeb(), getTitleFromWeb(), getPictureFromWeb(), getIngredientFromWeb(), getStepFromWeb());

        } catch (Exception e) {
            System.err.println("Parser : " + e);
        }
    }

    private int getRecipeTimeFromWeb() {
        if (!(this.documentWeb.select(marmiton.getRecipeTimePathSelector()).isEmpty())) {
            String str = this.documentWeb.select(marmiton.getRecipeTimePathSelector()).get(0).ownText();
            Tools tools = new Tools();
            return tools.parseTime(str);
        } else {
            return 0;
        }

    }

    private int getCookingTimeFromWeb() {

        if (!(this.documentWeb.select(marmiton.getCookingTimePathSelector()).isEmpty())) {
            String str = this.documentWeb.select(marmiton.getCookingTimePathSelector()).get(0).ownText();
            Tools tools = new Tools();
            return tools.parseTime(str);
        } else {
            return 0;
        }
    }

    private int getPreparationTime() {
        if (!(this.documentWeb.select(marmiton.getPreparationTimePathSelector()).isEmpty())) {
            String str = this.documentWeb.select(marmiton.getPreparationTimePathSelector()).get(0).ownText();
            Tools tools = new Tools();
            return tools.parseTime(str);
        } else {
            return 0;
        }
    }

    private int getNumberPersonFromWeb() {
        if (!(this.documentWeb.select(marmiton.getNumberPersonPathSelector()).isEmpty())) {
            return (Integer.parseInt(this.documentWeb.select(marmiton.getNumberPersonPathSelector()).get(0).ownText()));
        } else {
            return 0;
        }
    }

    private float getMarkFromWeb() {
        if (!(this.documentWeb.select(marmiton.getEconomicLevelPathSelector()).isEmpty())) {
            return Float.parseFloat(this.documentWeb.select(marmiton.getMarkPathSelector()).get(0).ownText());
        } else {
            return 0;
        }
    }

    private String getEconomicLevelFromWeb() {
        if (!(this.documentWeb.select(marmiton.getEconomicLevelPathSelector()).isEmpty())) {
            return (this.documentWeb.select(marmiton.getEconomicLevelPathSelector()).get(0).ownText());
        } else {
            return null;
        }
    }

    private String getDifficultyLevelFromWeb() {
        if (!(this.documentWeb.select(marmiton.getDifficultyLevelPathSelector()).isEmpty())) {
            return this.documentWeb.select(marmiton.getDifficultyLevelPathSelector()).get(0).ownText();
        } else {
            return null;
        }
    }

    private String getTitleFromWeb() {
        if (!(this.documentWeb.select(marmiton.getTitlePathSelector()).isEmpty())) {
            return this.documentWeb.select(marmiton.getTitlePathSelector()).get(0).ownText();
        } else {
            return null;
        }
    }

    private String getPictureFromWeb() {
        if (!(this.documentWeb.select(marmiton.getPicturesPathSelector()).isEmpty())) {
            return this.documentWeb.select(marmiton.getPicturesPathSelector()).attr("src");
        } else {
            return null;
        }
    }

    private ArrayList getIngredientFromWeb() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        for (Element element : this.documentWeb.select(marmiton.getIngredientPathSelector())) {
            Ingredient ingredient = new Ingredient();
            Tools tools = new Tools();
            ingredient.setName(tools.ingredient(element.getElementsByClass("ingredient").get(0).ownText()));
            if (element.getElementsByClass("recipe-ingredient-qt").get(0).ownText().equals("")) {
                ingredient.setIngQuantities(1);
            } else {
                ingredient.setIngQuantities(Float.parseFloat(element.getElementsByClass("recipe-ingredient-qt").get(0).attr("data-base-qt")));

            }
            ingredient.setUnits(tools.unit(element.getElementsByClass("ingredient").get(0).ownText()));
            ingredient.setUrl(element.getElementsByClass("ingredients-list__item__icon").attr("src"));
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private ArrayList getStepFromWeb() {
        ArrayList<Step> steps = new ArrayList<Step>();
        for (Element element : this.documentWeb.select(marmiton.getStepPathSelector())) {
            Step step = new Step();
            String[] etape = element.select(" h3.__secondary").get(0).ownText().split(" ");
            step.setStepNumber(Integer.parseInt(etape[1]));
            step.setDescription(element.getElementsByClass("recipe-preparation__list__item").get(0).ownText());
            steps.add(step);
        }
        return steps;
    }


}
