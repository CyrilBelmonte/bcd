package com.ucp.cleaners;

import com.ucp.cookwithease.dao.DAOFactory;

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
        LinkedList<String> excludedAdjectives = new LinkedList<>(Arrays.asList(
            "petit[e]?[s]?", "grand[e]?[s]?", "gros(?:se)?[s]?", "bon(?:ne)?[s]?",
            "fin[e]?[s]?", "beau[x]?", "belle[s]?", "min[i]?(?:mum)?[s]?", "max[i]?(?:mum)?[s]?",
            "cher[s]?", "géant[s]?", "généreux", "facile[s]?", "rapide[s]?", "simple[s]?",
            "simplifié[e]?[s]?", "super(?:be)?[s]?", "réussi(?:e|te)?[s]?", "parfait[e]?[s]?",
            "vrai[e]?[s]?", "véritable[s]?", "vite[s]?", "inratable[s]?", "délicieu(?:x|se[s]?)",
            "moelleuse[s]?", "délice[s]?", "enti(?:er|ière)[s]?", "express", "fau(?:x|sse[s]?)",
            "onctueu(?:x|se[s]?)", "savoureu(?:x|se[s]?)", "original[e]?[s]?", "préféré[e]?[s]?",
            "parfum[é]?[e]?[s]?", "magique[s]?", "individuel(?:le)?[s]?", "cuit[e]?[s]?",
            "garni[e]?[s]?", "gourmand[e]?[s]?", "doux", "brut[s]?", "épais(?:se)?[s]?",
            "chaud[e]?[s]?", "froid[e]?[s]?", "fra[iî]che[s]?", "gelé[e]?[s]?", "glacé[e]?[s]?",
            "cassé[e]?[s]?", "haché[e]?[s]?", "court[e]?[s]?", "long(?:ue)?[s]?", "fort[e]?[s]?",
            "gras(?:se)?[s]?", "moyen(?:ne)?[s]?", "tiède[s]?", "excellent[e]?[s]?",
            "délicat(?:ement)?[s]?", "irrésistible[s]?"
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
            "toi", "ton", "tu", "un[e]?", "vos", "votre", "vous", "ça", "sans", "&", "\\+",
            "étant", "suis", "es[t]?", "sommes", "êtes", "sont", "soi[ts]?", "ayant",
            "eu[e]?[s]?", "ai", "as", "avons", "avez", "ceci", "cela", "cet", "tr[éeè]s",
            "cette", "ici", "quel(?:le)?[s]?", "tou(?:s|t[e]?[s]?)", "comme(?:nt)?",
            "peu", "trop", "ultra", "extra", "moins", "plus", "pourtant", "fait[e]?[s]?",
            "moitié[s]?", "demi[e]?[s]?", "non", "même[s]?", "chez", "entre", "sous",
            "dessus"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        stopWordsPattern = buildPattern(regex, excludedStopWords);
    }

    private void initUnwantedUnitsPattern() {
        LinkedList<String> excludedWords = new LinkedList<>(Arrays.asList(
            "ml", "cl", "dl", "l", "mg", "g", "kg",
            "cuillère[s]?(?: à (?:soupe|café))?", "tasse[s]?(?: à café)?", "bol[s]?", "boule[s]?",
            "tranche[s]?", "branche[s]?", "feuille[s]?", "bouquet[s]?", "brin[s]?", "botte[s]?",
            "fleur[s]?", "gousse[s]?", "b[uû]che[s]?", "b[aâ]ton[s]?", "verre[s]?", "brique[s]?",
            "briquette[s]?", "part[s]?", "bloc[s]?", "barquette[s]?", "pot[s]?", "bo[iî]te[s]?",
            "sachet[s]?", "paquet[s]?", "rouleau[x]?", "grain[e]?[s]?", "poignée[s]?", "pincée[s]?",
            "zeste[s]?", "portion[s]?", "morceau[s]?", "filet[s]?", "cuisse[s]?", "pointe[s]?",
            "bouteille[s]?", "dose[s]?", "quartier[s]?", "goutte[s]?", "extrait[s]?", "flocon[s]?",
            "tablette[s]?", "plaque[s]?", "cube[s]?", "carré[s]?", "blanc[s]?", "jaune[s]?",
            "bocal[s]?", "brisure[s]?", "pavé[s]?", "pilon[s]?", "coeur[s]?", "épaule[s]?",
            "escalope[s]?", "aiguillette[s]?", "rondelle[s]?", "louche[s]?", "bûchette[s]?"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedUnitsPattern = buildPattern(regex, excludedWords);
    }

    private void initUnwantedWordsPattern() {
        LinkedList<String> excludedWords = new LinkedList<>(Arrays.asList(
            "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
            "bleu[e]?[s]?", "blanc[s]?", "rouge[s]?", "noir[e]?[s]?", "violet[e]?[s]?",
            "jaune[s]?", "vert[e]?[s]?", "gris[e]?[s]?", "marron[s]?", "rose[s]?", "brun[e]?[s]?",
            "lundi[s]?", "mardi[s]?", "mercredi[s]?", "jeudi[s]?", "vendredi[s]?", "samedi[s]?",
            "dimanche[s]?", "jour[s]?", "assortiment[s]?", "autocuiseur[s]?", "aérienne[s]?",
            "barbecue[s]?", "barre[s]?", "base[s]?", "bite[s]?", "bonhomme[s]?", "chomeur[s]?",
            "christmas", "cigarette[s]?", "coiffé[e]?[s]?", "coupe[s]?", "crotte[s]?",
            "cuillère[s]?", "cuisson[s]?", "différent[e]?[s]?", "doré[e]?[s]?", "double[s]?",
            "egg[s]?", "fa[çc]on[s]?", "fagot[s]?", "fer[s]?", "folle[s]?", "food", "four[s]?",
            "garniture[s]?", "goût[s]?", "idée[s]?", "lion[s]?", "mag[s]?", "graisse[s]?",
            "maison[s]?", "maman[s]?", "mamitou[s]?", "manger", "mcmuffin[s]?", "monté[e]?[s]?",
            "multicolore[s]?", "mystère[s]?", "mère[s]?", "noire[s]?", "nul[s]?", "partager",
            "partage[s]?", "pièce[s]?", "pépite[s]?", "rit", "roi[s]?", "rondelle[s]?", "rose[s]?",
            "saint[s]?", "saveur[s]?", "souhait[s]?", "sphère[s]?", "spirale[s]?", "sucré[e]?[s]?",
            "surprise[s]?", "tartiner", "tasse[s]?", "tendresse[s]?", "tigré[s]?", "tricolore[s]?",
            "wok[s]?", "zébré[s]?", "écoliers[s]?", "gourmandise[s]?", "apéro", "dessert[s]?",
            "mélange[s]?", "concentré[e]?[s]?", "fermenté[e]?[s]?", "r[aâ]pé[e]?[s]?", "reste[s]?",
            "coulis", "poudre[s]?", "fumé[e]?[s]?", "cru[e]?[s]?", "liquide[s]?", "amère[s]?",
            "brassé[e]?[s]?", "chair[s]?", "sec[s]?", "pelé[e]?[s]?", "pulpe[s]?", "sauvage[s]?",
            "entrée[s]?", "plat[s]?", "dessert[s]?", "authentique[s]?", "fouetté[e]?[s]?",
            "minute[s]?", "heure[s]?", "diabolique[s]?", "assiette[s]?", "couleur[s]?", "machine[s]?",
            "duo", "trio", "panier[s]?", "mamie[s]?", "autonome[s]?", "faire", "preparer", "sel",
            "salé[e]?[s]?", "poivr[ée][e]?[s]?", "sucr[ée][e]?[s]?", "blonde[s]?", "bouchée[s]?",
            "copeau[x]?", "couteau[x]?", "dé[s]?", "rase", "nature[s]?", "concassé[e]?[s]?",
            "étoile[s]?", "autre[s]?", "confit[e]?[s]?", "collier[s]?", "alimentaire[s]?", "mmm",
            "côte(?:lette)?[s]?", "pépin[s]?", "pépite[s]?", "boulanger[s]?", "chimique[s]?",
            "matière[e]?[s]?", "ancien(?:ne)?[s]?", "campagne[s]?", "complet[s]?", "complète[s]?",
            "pied[s]?", "sauté[e]?[s]?", "seché[e]?[s]?", "famil(?:lia)?le[s]?", "nouveau[x]?",
            "nouvel(?:le)?[s]?", "boulet[t]?[e]?[s]?", "amélioré[e]?[s]?", "simplement", "pays",
            "revisit[ée][e]?[s]?", "braisé[e]?[s]?", "cent[s]?", "mille[s]?", "timbale[s]?", "semi",
            "recette[s]?", "simplissime[s]?", "sublime[s]?", "champ[s]?", "jeune[s]?", "fixe[s]?",
            "instantané[e]?[s]?", "lyophilisé[e]?[s]?", "soluble[s]?", "cerneau[x]?", "couverture[s]?",
            "gazeuse[s]?", "gazéifié[e]?[s]?", "pétillant[e]?[s]?", "essence[s]?", "fermentante[s]?",
            "gonflé[e]?[s]?", "aérien(?:ne)?[s]?", "and", "apériti(?:f|ve)[s]?", "vie[s]?", "miam",
            "terrain[s]?", "version[s]?", "miroir[s]?", "classique[s]?", "coeur[s]?", "croute[s]?",
            "peau[x]?", "eclat[s]?", "ecorse[s]?", "damier[s]?", "new", "ans", "année[s]?",
            "forme[s]?", "meilleur[e]?[s]?", "cuiller[s]?", "effet[s]?", "fleur[s]?", "pet[s]?",
            "pur[s]?", "multi[s]?", "bien", "m[eé]ga[s]?", "style[s]?", "doigt[s]?", "dej", "dit",
            "succès", "made", "merveille[u]?[sx]?[e]?[s]?", "tradition[s]", "levé[e]?[s]?",
            "riche[s]?", "tata", "tomber", "mix[s]?", "intérieur[e]?[s]?", "onde[s]?", "trou[s]?",
            "type[s]?", "mort[e]?[s]?", "edulcorant[s]?", "bombe[s]?", "fond[s]?"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedWordsPattern = buildPattern(regex, excludedWords);
    }

    private void initUnwantedNamesPattern() {
        LinkedList<String> excludedNames = new LinkedList<>(Arrays.asList(
            "jacqueline", "jeanne", "brigitte", "marielle", "mike", "mag", "nadine(?:tte)", "lili",
            "hélène", "sophie", "sylvie", "tom", "momo", "marie", "valentin", "chris", "denis",
            "julie", "josé", "marcel", "anne", "christine", "clémentine", "valérie", "eug[eé]nie",
            "jean", "elvire"
        ));

        String regex = "(^|\\b)({TERMS})(\\b|$)";

        unwantedNamesPattern = buildPattern(regex, excludedNames);
    }

    public void initIngredientsPattern() {
        LinkedList<String> excludedIngredients = DAOFactory.getIngredientDAO().getAllIngredients();
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
