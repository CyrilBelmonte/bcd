package com.ucp.recipecleaner;

import com.ucp.cookwithease.dao.DAOFactory;

import java.util.Arrays;
import java.util.LinkedList;


public class RecipeCleaner {
    private String buildRegex(String pattern, LinkedList<String> terms) {
        StringBuilder regexBuilder = new StringBuilder();
        String pipe = "";

        for (String term : terms) {
            regexBuilder.append(pipe);
            regexBuilder.append(term);
            pipe = "|";
        }

        return pattern.replace("{TERMS}", regexBuilder);
    }

    public String deleteAdjectivesFromString(String sentence) {
        LinkedList<String> excludedAdjectives = new LinkedList<>(Arrays.asList(
            "petit[e]?[s]?", "grand[e]?[s]?", "gros(?:se)?[s]?", "bon(?:ne)?[s]?",
            "fin[e]?[s]?", "beau[x]?", "belle[s]?", "mini(?:mum)?[s]?", "maxi(?:mum)?[s]?",
            "cher[s]?", "géant[s]?", "généreux", "facile[s]?", "rapide[s]?", "simple[s]?",
            "simplifié[e]?[s]?", "super(?:be)?[s]?", "réussi(?:e|te)?[s]?", "parfait[e]?[s]?",
            "vrai[e]?[s]?", "véritable[s]?", "vite[s]?", "inratable[s]?", "délicieu(?:x|se[s]?)",
            "moelleuse[s]?", "délice[s]?", "enti(?:er|ière)[s]?", "express", "fau(?:x|sse[s]?)",
            "onctueu(?:x|se[s]?)", "savoureu(?:x|se[s]?)", "original[e]?[s]?", "préféré[e]?[s]?",
            "parfumée[e]?[s]?", "magique[s]?", "individuel(?:le)?[s]?", "cuit[e]?[s]?",
            "garni[e]?[s]?", "gourmand[e]?[s]?"
        ));

        String pattern = "(^|\\b)({TERMS})(\\b|$)";

        String regex = buildRegex(pattern, excludedAdjectives);

        return sentence.toLowerCase().replaceAll(regex, " ");
    }

    public String deleteStopWordsFromString(String sentence) {
        LinkedList<String> excludedStopWords = new LinkedList<>(Arrays.asList(
            "au[x]?", "avec", "ce[s]?", "dans", "de[s]?", "du", "elle[s]?", "en", "et",
            "eux", "il[s]?", "je", "la", "leur[s]?", "le[s]?", "lui", "mais", "ma", "même",
            "me[s]?", "moi", "mon", "ne", "nos", "notre", "nous", "on[t]?", "ou", "par",
            "pas", "pour", "que", "qui", "sa", "se[s]?", "son", "sur", "ta", "te[s]?",
            "toi", "ton", "tu", "un[e]?", "vos", "votre", "vous", "ça", "sans",
            "étant", "suis", "es[t]?", "sommes", "êtes", "sont", "soi[ts]?", "ayant",
            "eu[e]?[s]?", "ai", "as", "avons", "avez", "ceci", "cela", "cet",
            "cette", "ici", "quel(?:le)?[s]?", "tou(?:s|t[es]?)", "comme", "très", "peu",
            "trop", "ultra", "extra", "moins", "plus", "pourtant", "fait[e]?[s]?"
        ));

        String pattern = "(^|\\b)({TERMS})(\\b|$)";

        String regex = buildRegex(pattern, excludedStopWords);

        return sentence.toLowerCase().replaceAll(regex, " ");
    }

    public String deleteUnwantedWordsFromString(String sentence) {
        LinkedList<String> excludedWords = new LinkedList<>(Arrays.asList(
            "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
            "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche", "jour",
            "and", "apériti(?:f|ve)[s]?", "assortiment[s]?", "autocuiseur[s]?", "aérienne[s]?",
            "barbecue[s]?", "barre[s]?", "base[s]?", "bite[s]?", "bonhomme[s]?", "chomeur[s]?",
            "christmas", "cigarette[s]?", "coiffé[e]?[s]?", "coupe[s]?", "crotte[s]?",
            "cuillère[s]?", "cuisson[s]?", "différent[e]?[s]?", "doré[e]?[s]?", "double[s]?",
            "egg[s]?", "fa[çc]on[s]?", "fagot[s]?", "fer[s]?", "folle[s]?", "food", "four[s]?",
            "garniture[s]?", "goût[s]?", "graine[s]?", "idée[s]?", "lion[s]?", "mag[s]?",
            "maison[s]?", "maman[s]?", "mamitou[s]?", "manger", "mcmuffin[s]?", "monté[e]?[s]?",
            "multicolore[s]?", "mystère[s]?", "mère[s]?", "noire[s]?", "nul[s]?", "partager",
            "partage[s]?", "pièce[s]?", "pépite[s]?", "rit", "roi[s]?", "rondelle[s]?", "rose[s]?",
            "saint[s]?", "saveur[s]?", "souhait[s]?", "sphère[s]?", "spirale[s]?", "sucré[e]?[s]?",
            "surprise[s]?", "tartiner", "tasse[s]?", "tendresse[s]?", "tigré[s]?", "tricolore[s]?",
            "wok[s]?", "zébré[s]?", "écoliers[s]?", "gourmandise[s]?", "dessert[s]?"
        ));

        String pattern = "(^|\\b)({TERMS})(\\b|$)";

        String regex = buildRegex(pattern, excludedWords);

        return sentence.toLowerCase().replaceAll(regex, " ");
    }

    public String deleteUnwantedNamesFromString(String sentence) {
        LinkedList<String> excludedNames = new LinkedList<>(Arrays.asList(
            "jacqueline", "jeanne", "brigitte", "marielle", "mike", "mag", "nadine", "lili",
            "hélène", "sophie"
        ));

        String pattern = "(^|\\b)({TERMS})(\\b|$)";

        String regex = buildRegex(pattern, excludedNames);

        return sentence.toLowerCase().replaceAll(regex, " ");
    }

    public String deleteIngredientsFromString(String sentence) {
        LinkedList<String> excludedIngredients = DAOFactory.getIngredientDAO().getAllIngredients();

        String pattern = "(^|\\b)({TERMS})(\\b|$)";

        String regex = buildRegex(pattern, excludedIngredients);

        return sentence.toLowerCase().replaceAll(regex, " ");
    }

    public String deleteShortWordsFromString(String sentence) {
        String regex = "(^|\\s+)(.{1,2})(\\s+|$)";

        return sentence.replaceAll(regex, " ");
    }

    public String deleteParenthesesFromString(String sentence) {
        String regex = "[(\\[].*?[)\\]]";

        return sentence.replaceAll(regex, " ");
    }

    public String deletePunctuationFromString(String sentence) {
        String regex = "[-.,;:!?*\"'’«»()\\[\\]/]";

        return sentence.replaceAll(regex, " ");
    }

    public String reduceWhiteSpacesFromString(String sentence) {
        String regex = "\\s+";
        String beginEndRegex = "(^\\s+|\\s+$)";

        return sentence.replaceAll(regex, " ")
                       .replaceAll(beginEndRegex, "");
    }


    public String[] getStandardizedRecipeName(String recipeName) {
        String standardizedRecipeName = reduceWhiteSpacesFromString(
           deleteUnwantedNamesFromString(
              deleteUnwantedWordsFromString(
                 deleteIngredientsFromString(
                    deleteAdjectivesFromString(
                       deleteShortWordsFromString(
                          deleteStopWordsFromString(
                             deletePunctuationFromString(
                                deleteParenthesesFromString(recipeName)))))))));

        String[] words = standardizedRecipeName.split(" ");

        /*
        System.out.println(standardizedRecipeName);

        System.err.println("------------");
        for (String word : words) {
            System.err.println("| " + word);
        }

        System.err.println("------------");
        */

        return words;
    }
}
