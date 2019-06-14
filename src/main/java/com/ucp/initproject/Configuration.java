package com.ucp.initproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Configuration {
    private Configuration() {}

    public static LinkedList<Integer> getRecipesToIndex() {
        String fileName = "./src/main/java/com/ucp/initproject/RecipesToIndex";

        LinkedList<Integer> recipesToIndex = new LinkedList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",\\s?");

                for (String value : values) {
                    recipesToIndex.addLast(Integer.parseInt(value));
                }
            }

        } catch (IOException e) {
            System.out.println("[ERROR] getRecipesToIndex : " + e.getMessage());
        }

        return recipesToIndex;
    }
}
