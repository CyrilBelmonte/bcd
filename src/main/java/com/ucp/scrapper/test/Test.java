package com.ucp.scrapper.test;

import com.ucp.scrapper.engine.Parser;


public class Test {
    public static void main(String[] args) {
        int nbr = 100;
        System.out.println("##################################################################################################################################################################\n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < nbr; i++) {
            String url = "https://marmiton.org/recettes/recette-hasard.aspx";
            Parser parser = new Parser(url);
            System.out.println(parser.getRecipe());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
    }
}
