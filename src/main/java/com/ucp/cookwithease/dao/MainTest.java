package com.ucp.cookwithease.dao;

import com.ucp.cookwithease.model.*;
import com.ucp.cookwithease.tools.Tools;

import java.util.Date;
import java.util.LinkedList;


public class MainTest {
    public static void main(String[] args) {
        User user = new User();

        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPseudo("jdoe");
        user.setEmail("john.doe@u-cergy.fr");
        user.setPassword(Tools.sha256("password"));
        user.setInscriptionDate(new Date());

        System.out.println(DAOFactory.getUserDAO().insert(user));
        System.out.println(user.getId());


        Comment comment = new Comment();

        comment.setUserID(user.getId());
        comment.setRecipeID(1);
        comment.setDescription("Génial !");
        comment.setRating(5);
        comment.setPublicationDate(new Date());

        System.out.println(DAOFactory.getCommentDAO().insert(comment));


        ///////////////////////////////////////////////////////////////////

        Recipe cremeChantilly = new Recipe();
        cremeChantilly.setName("Crème chantilly légère");
        cremeChantilly.setDuration(15);
        cremeChantilly.setPersons(3);
        cremeChantilly.setType(DishType.DESSERT);
        cremeChantilly.setCost(Level.LOW);
        cremeChantilly.setDifficulty(Level.LOW);
        cremeChantilly.setRating(3.5f);
        cremeChantilly.setPicture("https://assets.afcdn.com/recipe/20160928/43116_w420h344c1cx1920cy2880.jpg");

        Ingredient cremeFraiche = new Ingredient();
        cremeFraiche.setName("Crème fraiche");
        cremeFraiche.setQuantity(50);
        cremeFraiche.setUnit("cl");

        Ingredient blancsOeuf = new Ingredient();
        blancsOeuf.setName("Blancs d'oeuf");
        blancsOeuf.setQuantity(3);
        blancsOeuf.setUnit(null);

        Ingredient sucreVanille = new Ingredient();
        sucreVanille.setName("Sucre vanillé");
        sucreVanille.setQuantity(1);
        sucreVanille.setUnit("sachet");

        Step step1 = new Step();
        step1.setPosition(1);
        step1.setDescription("Fouetter la crème dans un saladier avec les 2 sachets de fixe-chantilly, le sucre vanillé et l'édulcorant.");

        Step step2 = new Step();
        step2.setPosition(2);
        step2.setDescription("Mélanger délicatement avec les blancs d'oeuf et mettre au frais.");

        cremeChantilly.addIngredient(cremeFraiche);
        cremeChantilly.addIngredient(blancsOeuf);
        cremeChantilly.addIngredient(sucreVanille);
        cremeChantilly.addStep(step1);
        cremeChantilly.addStep(step2);

        System.out.println(DAOFactory.getRecipeDAO().insert(cremeChantilly));


        ///////////////////////////////////////////////////////////////////


        Recipe recipe = DAOFactory.getRecipeDAO().find(cremeChantilly.getId());

        System.out.println(recipe);
    }
}
