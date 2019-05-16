package com.ucp.recipecleaner;

import com.ucp.cookwithease.dao.DAOFactory;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;


public class RecipeCleaner {
    private Pattern adjectivesPattern;
    private Pattern stopWordsPattern;
    private Pattern unwantedWordsPattern;
    private Pattern unwantedNamesPattern;
    private Pattern ingredientsPattern;
    private Pattern shortWordsPattern;
    private Pattern parenthesesPattern;
    private Pattern punctuationPattern;
    private Pattern accentsPattern;
    private Pattern whiteSpacesPattern;
    private Pattern whiteSpacesBEPattern;

    public RecipeCleaner() {
        initAdjectivesPattern();
        initStopWordsPattern();
        initUnwantedWordsPattern();
        initUnwantedNamesPattern();
        initIngredientsPattern();
        initShortWordsPattern();
        initParenthesesPattern();
        initPunctuationPattern();
        initAccentsPattern();
        initWhiteSpacesPattern();
    }

    private Pattern buildPattern(String regex) {
        return buildPattern(regex, null);
    }

    private Pattern buildPattern(String regex, LinkedList<String> terms) {
        if (terms != null) {
            StringBuilder regexBuilder = new StringBuilder();
            String pipe = "";

            for (String term : terms) {
                regexBuilder.append(pipe);
                regexBuilder.append(term);
                pipe = "|";
            }

            regex = regex.replace("{TERMS}", regexBuilder);
        }

        return Pattern.compile(regex);
    }

    private void initAdjectivesPattern() {
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

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        adjectivesPattern = buildPattern(regex, excludedAdjectives);
    }

    private void initStopWordsPattern() {
        LinkedList<String> excludedStopWords = new LinkedList<>(Arrays.asList(
            "au[x]?", "avec", "ce[s]?", "dans", "de[s]?", "du", "elle[s]?", "en", "et",
            "eux", "il[s]?", "je", "la", "leur[s]?", "le[s]?", "lui", "mais", "ma", "même",
            "me[s]?", "moi", "mon", "ne", "nos", "notre", "nous", "on[t]?", "ou", "par",
            "pas", "pour", "que", "qui", "sa", "se[s]?", "son", "sur", "ta", "te[s]?",
            "toi", "ton", "tu", "un[e]?", "vos", "votre", "vous", "ça", "sans", "&",
            "étant", "suis", "es[t]?", "sommes", "êtes", "sont", "soi[ts]?", "ayant",
            "eu[e]?[s]?", "ai", "as", "avons", "avez", "ceci", "cela", "cet",
            "cette", "ici", "quel(?:le)?[s]?", "tou(?:s|t[es]?)", "comme", "très", "peu",
            "trop", "ultra", "extra", "moins", "plus", "pourtant", "fait[e]?[s]?"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        stopWordsPattern = buildPattern(regex, excludedStopWords);
    }

    private void initUnwantedWordsPattern() {
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
            "wok[s]?", "zébré[s]?", "écoliers[s]?", "gourmandise[s]?", "apéro", "dessert[s]?"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedWordsPattern = buildPattern(regex, excludedWords);
    }

    private void initUnwantedNamesPattern() {
        LinkedList<String> excludedNames = new LinkedList<>(Arrays.asList(
            "jacqueline", "jeanne", "brigitte", "marielle", "mike", "mag", "nadine", "lili",
            "hélène", "sophie"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedNamesPattern = buildPattern(regex, excludedNames);
    }

    public void initIngredientsPattern() {
        LinkedList<String> excludedIngredients = DAOFactory.getIngredientDAO().getAllIngredients();

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        ingredientsPattern = buildPattern(regex, excludedIngredients);
    }

    private void initShortWordsPattern() {
        String regex = "(^|\\b)(.{1,2})(\\b|$)";

        shortWordsPattern = buildPattern(regex);
    }

    private void initParenthesesPattern() {
        String regex = "[(\\[].*?[)\\]]";

        parenthesesPattern = buildPattern(regex);
    }

    private void initPunctuationPattern() {
        String regex = "[-.,;:!?*\"'’«»()\\[\\]/]";

        punctuationPattern = buildPattern(regex);
    }

    private void initAccentsPattern() {
        String regex = "[\\p{M}]";

        accentsPattern = buildPattern(regex);
    }

    private void initWhiteSpacesPattern() {
        String regex = "\\s+";
        String regexBE = "(^\\s+|\\s+$)";

        whiteSpacesPattern = buildPattern(regex);
        whiteSpacesBEPattern = buildPattern(regexBE);
    }

    private String deleteAdjectivesFromString(String sentence) {
        return adjectivesPattern.matcher(sentence).replaceAll(" ");
    }

    private String deleteStopWordsFromString(String sentence) {
        return stopWordsPattern.matcher(sentence).replaceAll(" ");
    }

    private String deleteUnwantedWordsFromString(String sentence) {
        return unwantedWordsPattern.matcher(sentence).replaceAll(" ");
    }

    private String deleteUnwantedNamesFromString(String sentence) {
        return unwantedNamesPattern.matcher(sentence).replaceAll(" ");
    }

    public String deleteIngredientsFromString(String sentence) {
        return ingredientsPattern.matcher(sentence).replaceAll(" ");
    }

    private String deleteShortWordsFromString(String sentence) {
        return shortWordsPattern.matcher(sentence).replaceAll(" ");
    }

    private String deleteParenthesesFromString(String sentence) {
        return parenthesesPattern.matcher(sentence).replaceAll(" ");
    }

    private String deletePunctuationFromString(String sentence) {
        return punctuationPattern.matcher(sentence).replaceAll(" ");
    }

    private String deleteAccentsFromString(String sentence) {
        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);

        return accentsPattern.matcher(sentence).replaceAll("");
    }

    private String reduceWhiteSpacesFromString(String sentence) {
        return whiteSpacesBEPattern.matcher(
           whiteSpacesPattern.matcher(sentence).replaceAll(" ")
        ).replaceAll("");
    }

    public LinkedList<String> getCleanedRecipeName(String recipeName) {
        recipeName = recipeName.toLowerCase();

        String standardizedRecipeName =
            reduceWhiteSpacesFromString(
            deleteUnwantedNamesFromString(
            deleteUnwantedWordsFromString(
            deleteAccentsFromString(
            deleteIngredientsFromString(
            deleteAdjectivesFromString(
            deleteShortWordsFromString(
            deleteStopWordsFromString(
            deletePunctuationFromString(
            deleteParenthesesFromString(recipeName)
        )))))))));

        if (standardizedRecipeName.isEmpty()) {
            return new LinkedList<>();
        }

        LinkedList<String> results = new LinkedList<>(
           Arrays.asList(standardizedRecipeName.split(" ")));

        return results;
    }
}
