package com.ucp.ai_experiments;

import com.ucp.recipecleaner.AIEntries;

import java.util.HashMap;


public class Constants {
    private static HashMap<String, Double> maxIngredientsQuantity;

    private Constants() {}

    public static double getQuantityWithUnit() {
        if (maxIngredientsQuantity == null) {
            maxIngredientsQuantity = AIEntries.getMaxIngredientsQuantity();
        }

        return maxIngredientsQuantity.get("quantityWithUnit");
    }

    public static double getQuantityWithoutUnit() {
        if (maxIngredientsQuantity == null) {
            maxIngredientsQuantity = AIEntries.getMaxIngredientsQuantity();
        }

        return maxIngredientsQuantity.get("quantityWithoutUnit");
    }
}
