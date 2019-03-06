package com.ucp.scrapper.data.WebConnection;

import lombok.Data;

@Data
public class Marmiton {
    private String ingredientPathSelector;
    private String ingredientQtsPathSelector;
    private String ingredientNamePathSelector;
    private String ingredientPicturePathSelector;

    private String stepPathSelector;
    private String stepNumPathSelector;
    private String stepDescPathSelector;

    private String commentPathSelector;
    private String commentPseudoSelector;
    private String commentMarkPathSelector;
    private String commentCommentPathSelector;

    private String titlePathSelector;

    private String cookingTimePathSelector;
    private String preparationTimePathSelector;
    private String recipeTimePathSelector;

    private String numberPersonPathSelector;

    private String markPathSelector;

    private String difficultyLevelPathSelector;
    private String economicLevelPathSelector;

    private String picturesPathSelector;

    public Marmiton() {
        this.ingredientPathSelector = "ul.recipe-ingredients__list li";

        this.stepPathSelector = " ol.recipe-preparation__list li";

        this.cookingTimePathSelector = "#sticky-desktop-only > div.padded-content.col-center-special-desktop > div.recipe-infos__timmings > div.recipe-infos__timmings__detail > div.recipe-infos__timmings__cooking > span";
        this.preparationTimePathSelector = "#sticky-desktop-only > div.padded-content.col-center-special-desktop > div.recipe-infos__timmings > div.recipe-infos__timmings__detail > div.recipe-infos__timmings__preparation > span.recipe-infos__timmings__value";
        this.recipeTimePathSelector = "#sticky-desktop-only > div.padded-content.col-center-special-desktop > div.recipe-infos__timmings > div.recipe-infos__timmings__total-time.title-4 > span";

        this.numberPersonPathSelector = "#sticky-desktop-only > div.af-cols > div.af-col-center > div.padded-content.recipe-infos__container > div.recipe-infos > div.recipe-infos__quantity > span.title-2.recipe-infos__quantity__value";

        this.markPathSelector = "#post-review-container > div.mrtn-inline-block-middle.mrtn-hide-on-print > div > span.recipe-reviews-list__review__head__infos__rating__value";

        this.difficultyLevelPathSelector = "#sticky-desktop-only > div.af-cols > div.af-col-center > div.padded-content.recipe-infos__container > div.recipe-infos > div.recipe-infos__level > span";
        this.economicLevelPathSelector = "#sticky-desktop-only > div.af-cols > div.af-col-center > div.padded-content.recipe-infos__container > div.recipe-infos > div.recipe-infos__budget > span";

        this.titlePathSelector = "#content > div:nth-child(2) > h1";

        this.picturesPathSelector = "#af-diapo-desktop-0_img";


    }
}
