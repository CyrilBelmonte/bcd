package com.ucp.recipecleaner;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;


public class AITools {
    private static RecipeCleaner cleaner = new RecipeCleaner();

    private AITools() {}

    public static boolean contains(String recipeName, String word) {
        LinkedList<String> nameParts = cleaner.getCleanedRecipeName(recipeName);

        String standardizedRecipeName = StringUtils.join(nameParts, " ");

        return standardizedRecipeName.contains(word);
    }

    private static double convertToStandardUnit(double quantity, String unit) {
        double result = quantity;

        switch (unit) {
            case "mg":
            case "ml":
                result /= 1000;
                break;

            case "cl":
                result /= 100;
                break;

            case "dl":
                result /= 10;
                break;

            case "kg":
                result *= 1000;
                break;
        }

        return result;
    }

    public static double normalizeQuantity(double quantity, String unit) {
        LinkedList<String> standardUnits = new LinkedList<>(Arrays.asList(
            "ml", "cl", "dl", "l", "mg", "g", "kg"
        ));

        for (String standardUnit : standardUnits) {
            if (unit.equals(standardUnit)) {
                return convertToStandardUnit(quantity, standardUnit);
            }
        }

        if (unit.contains("cuillère")) {
            return quantity * 0.005;
        }

        if (unit.contains("louche")) {
            return quantity * 0.150;
        }

        if (unit.contains("tasse") ||
            unit.contains("verre")) {

            return quantity * 0.250;
        }

        if (unit.contains("bol")) {
            return quantity * 0.350;
        }

        if (unit.contains("brique")) {
            return quantity * 0.500;
        }

        if (unit.contains("portion") ||
            unit.contains("tranche") ||
            unit.contains("morceau") ||
            unit.contains("rondelle") ||
            unit.contains("part") ||
            unit.contains("dose")) {

            return quantity * 0.1;
        }

        if (unit.contains("barquette")) {
            return quantity * 10;
        }

        if (unit.contains("bouteille")) {
            return quantity;
        }

        if (unit.contains("quartier")) {
            return quantity * 0.25;
        }

        if (unit.contains("goutte")) {
            return quantity * 0.00005;
        }

        return quantity;
    }
}
