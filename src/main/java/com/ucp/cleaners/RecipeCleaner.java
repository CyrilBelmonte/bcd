package com.ucp.cleaners;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;


public class RecipeCleaner {
    private Pattern adjectivesPattern;
    private Pattern stopWordsPattern;
    private Pattern unwantedUnitsPattern;
    private Pattern unwantedWordsPattern;
    private Pattern unwantedNamesPattern;
    private Pattern ingredientsPattern;
    private Pattern shortWordsPattern;
    private Pattern parenthesesPattern;
    private Pattern punctuationPattern;
    private Pattern accentsPattern;
    private Pattern numbersPattern;
    private Pattern whiteSpacesPattern;
    private Pattern whiteSpacesBEPattern;

    public RecipeCleaner() {
        initAdjectivesPattern();
        initStopWordsPattern();
        initUnwantedUnitsPattern();
        initUnwantedWordsPattern();
        initUnwantedNamesPattern();
        initIngredientsPattern();
        initShortWordsPattern();
        initParenthesesPattern();
        initPunctuationPattern();
        initAccentsPattern();
        initNumbersPattern();
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
        LinkedList<String> excludedAdjectives = DictionaryReader.getAdjectivesDict();

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        adjectivesPattern = buildPattern(regex, excludedAdjectives);
    }

    private void initStopWordsPattern() {
        LinkedList<String> excludedStopWords = DictionaryReader.getStopWordsDict();

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        stopWordsPattern = buildPattern(regex, excludedStopWords);
    }

    private void initUnwantedUnitsPattern() {
        LinkedList<String> excludedWords = DictionaryReader.getUnitsDict();

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedUnitsPattern = buildPattern(regex, excludedWords);
    }

    private void initUnwantedWordsPattern() {
        LinkedList<String> excludedWords = DictionaryReader.getOtherWordsDict();

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedWordsPattern = buildPattern(regex, excludedWords);
    }

    private void initUnwantedNamesPattern() {
        LinkedList<String> excludedNames = DictionaryReader.getNamesDict();

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedNamesPattern = buildPattern(regex, excludedNames);
    }

    public void initIngredientsPattern() {
        LinkedList<String> excludedIngredients = DictionaryReader.getIngredientsDict();
        excludedIngredients.remove("");

        String regex = "(^|\\b)({TERMS})([ea]u[x]?|l[l]?[e]?[s]?|[s]?)?(\\b|$)";

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
        String regex = "[-+.,;:!?&*\"'’«»()\\[\\]/]";

        punctuationPattern = buildPattern(regex);
    }

    private void initAccentsPattern() {
        String regex = "[\\p{M}]";

        accentsPattern = buildPattern(regex);
    }

    private void initNumbersPattern() {
        String regex = "[0-9]";

        numbersPattern = buildPattern(regex);
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

    private String deleteUnwantedUnitsFromString(String sentence) {
        return unwantedUnitsPattern.matcher(sentence).replaceAll(" ");
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

    private String deleteNumbersFromString(String sentence) {
        return numbersPattern.matcher(sentence).replaceAll(" ");
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
            deleteIngredientsFromString(
            deleteNumbersFromString(
            deleteAccentsFromString(
            deleteUnwantedNamesFromString(
            deleteUnwantedWordsFromString(
            deleteUnwantedUnitsFromString(
            deleteAdjectivesFromString(
            deleteShortWordsFromString(
            deleteStopWordsFromString(
            deletePunctuationFromString(
            deleteParenthesesFromString(recipeName)
        )))))))))));

        if (standardizedRecipeName.isEmpty()) {
            return new LinkedList<>();
        }

        LinkedList<String> results = new LinkedList<>(
           Arrays.asList(standardizedRecipeName.split(" ")));

        return results;
    }

    public String getCleanedIngredientName(String ingredientName) {
        ingredientName = ingredientName.toLowerCase();

        String standardizedIngredientName =
            reduceWhiteSpacesFromString(
            deleteNumbersFromString(
            deleteAccentsFromString(
            deleteUnwantedWordsFromString(
            deleteAdjectivesFromString(
            deleteShortWordsFromString(
            deleteStopWordsFromString(
            deleteUnwantedUnitsFromString(
            deletePunctuationFromString(
            deleteParenthesesFromString(ingredientName)
        )))))))));

        return standardizedIngredientName;
    }
}
