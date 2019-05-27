package com.ucp.ai_experiments;

import com.ucp.recipecleaner.AIEntries;

import java.util.HashMap;


public class Constants {
    private static HashMap<String, Double> maxIngredientsQuantities;

    private Constants() {}

    public static HashMap<String, Double> getMaxIngredientsQuantities() {
        if (maxIngredientsQuantities == null) {
            maxIngredientsQuantities = AIEntries.getMaxIngredientsQuantities();
        }

        return maxIngredientsQuantities;
    }
}
