package com.ucp.recipecleaner;


import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;

public class AITools {
    private static RecipeCleaner cleaner = new RecipeCleaner();

    private AITools() {}

    public static boolean contains(String recipeName, String word) {
        LinkedList<String> nameParts = cleaner.getCleanedRecipeName(recipeName);

        String standardizedRecipeName = StringUtils.join(nameParts, " ");

        return standardizedRecipeName.contains(word);
    }
}
