package com.ucp.scrapper.Engine;

import com.ucp.scrapper.Data.RecipeData.Ingredient;
import com.ucp.scrapper.Data.RecipeData.Step;
import com.ucp.scrapper.Data.RecipeData.WebComment;
import lombok.Data;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class ParserMarmiton {

    Logger logger = Logger.getLogger("ParserMarmiton");
    private Document documentWeb;
    private Scrapper scrapper;

    private HashMap<String, Object> recipeParse;

    public ParserMarmiton(String url) {
        try {
            scrapper = new Scrapper(url);
            this.documentWeb = scrapper.getDocumentWeb();
        } catch (Exception e) {
            logger.error(this.getClass().getName() + " " + e);
        }
    }

    public void recipeElement() {

        HashMap<String, Object> hashMapHtlmElement = new HashMap<String, Object>();

        int recipeTime;
        int cookingTime;
        int preparationTime;
        int numberPersons;
        int mark;

        String economicLevel;
        String difficultyLevel;
        String title;
        String picture;

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ArrayList<WebComment> webComments = new ArrayList<WebComment>();
        ArrayList<Step> steps = new ArrayList<Step>();


        String[] tabRecipeTime = this.documentWeb.getElementsByClass("title-2 recipe-infos__total-time__value").get(0).ownText().split("h");
        if (tabRecipeTime.length == 2) {
            recipeTime = Integer.parseInt(tabRecipeTime[0]) * 60 + Integer.parseInt(tabRecipeTime[1]);
        } else {
            recipeTime = Integer.parseInt(tabRecipeTime[0]);
        }

        String[] tabCookingTime = this.documentWeb.getElementsByClass("recipe-infos__timmings__value").get(1).ownText().split("h");
        if (tabCookingTime.length == 2) {
            cookingTime = Integer.parseInt(tabCookingTime[0]) * 60 + Integer.parseInt(tabCookingTime[1]);
        } else {
            cookingTime = Integer.parseInt(tabCookingTime[0]);
        }

        String[] tabPreparationTime = this.documentWeb.getElementsByClass("recipe-infos__timmings__value").get(0).ownText().split("h");
        if (tabPreparationTime.length == 2) {
            preparationTime = Integer.parseInt(tabPreparationTime[0]) * 60 + Integer.parseInt(tabPreparationTime[1]);
        } else {
            preparationTime = Integer.parseInt(tabPreparationTime[0]);
        }

        numberPersons = Integer.parseInt(this.documentWeb.getElementsByClass("title-2 recipe-infos__quantity__value").get(0).ownText());
        mark = Integer.parseInt(this.documentWeb.getElementsByClass("recipe-reviews-list__review__head__infos__rating__value").get(0).ownText());

        economicLevel = this.documentWeb.getElementsByClass("recipe-infos__item-title").get(3).ownText();
        difficultyLevel = this.documentWeb.getElementsByClass("recipe-infos__item-title").get(4).ownText();

        title = this.getDocumentWeb().getElementsByClass("body-domuser-fr").get(0).getElementById("content").getElementsByClass("padded-content").get(0).getElementsByClass("main-title").get(0).ownText();

        picture = this.documentWeb.getElementById("af-diapo-desktop-0_img").getElementsByAttribute("src").val();

        for (Element elementIngredient : documentWeb.getElementsByClass("recipe-ingredients__list__item")) {

            Ingredient ingredient = new Ingredient();

            ingredient.setName(elementIngredient.getElementsByClass("ingredient").get(0).ownText());
            ingredient.setIngQuantities(Integer.parseInt(elementIngredient.getElementsByClass("recipe-ingredient-qt").get(0).ownText()));
            ingredient.setUrl(elementIngredient.getElementsByClass("ingredients-list__item__icon").get(0).getElementsByAttribute("src").attr("src"));
            ingredient.setUnits("");

            ingredients.add(ingredient);
        }

        for (Element elementComment : documentWeb.getElementsByClass("recipe-reviews-list__review__text ")) {
            WebComment webComment = new WebComment();

            webComment.setComment(elementComment.getElementsByClass("recipe-reviews-list__review__text").get(0).getElementsByTag("<p>").get(0).ownText());
            webComment.setMark(Integer.parseInt(elementComment.getElementsByClass("recipe-reviews-list__review__head__infos__rating__value").get(0).ownText()));
            webComment.setPseudo(elementComment.getElementsByClass("recipe-reviews-list__review__text").get(0).getElementsByTag("<p>").get(0).ownText());

            webComments.add(webComment);
        }

        for (Element elementStep : documentWeb.getElementsByClass("recipe-reviews-list__review__text ")) {
            Step step = new Step();

            step.setDescription(elementStep.getElementsByClass("").get(0).ownText());
            step.setStepNumber(Integer.parseInt(elementStep.getElementsByClass("").get(0).ownText()));

            steps.add(step);
        }

        this.recipeParse.put("ingredient", ingredients);
        this.recipeParse.put("webComment", webComments);
        this.recipeParse.put("step", steps);
        this.recipeParse.put("title", title);
        this.recipeParse.put("economicLevel", economicLevel);
        this.recipeParse.put("difficultyLevel", difficultyLevel);
        this.recipeParse.put("mark", mark);
        this.recipeParse.put("picture", picture);
        this.recipeParse.put("recipeTime", recipeTime);
        this.recipeParse.put("cookingTime", cookingTime);
        this.recipeParse.put("preparationTime", preparationTime);
        this.recipeParse.put("numberPersons", numberPersons);

    }
}
