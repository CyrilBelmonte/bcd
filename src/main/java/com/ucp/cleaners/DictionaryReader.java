package com.ucp.cleaners;

import com.ucp.cookwithease.dao.DAOFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class DictionaryReader {
    private static final String dictionariesPath = "./src/main/java/com/ucp/cleaners/dictionaries/";
    private static final String adjectivesDictPath = dictionariesPath + "adjectives.yaml";
    private static final String stopWordsDictPath = dictionariesPath + "stop_words.yaml";
    private static final String unitsDictPath = dictionariesPath + "units.yaml";
    private static final String otherWordsDictPath = dictionariesPath + "other_words.yaml";
    private static final String namesDictPath = dictionariesPath + "names.yaml";

    private DictionaryReader() {}

    public static LinkedList<String> getAdjectivesDict() {
        return readDictionary(adjectivesDictPath, "adjectives");
    }

    public static LinkedList<String> getStopWordsDict() {
        return readDictionary(stopWordsDictPath, "stop_words");
    }

    public static LinkedList<String> getUnitsDict() {
        return readDictionary(unitsDictPath, "units");
    }

    public static LinkedList<String> getOtherWordsDict() {
        return readDictionary(otherWordsDictPath, "other_words");
    }

    public static LinkedList<String> getNamesDict() {
        return readDictionary(namesDictPath, "names");
    }

    public static LinkedList<String> getIngredientsDict() {
        return DAOFactory.getIngredientDAO().getAllIngredients();
    }

    private static LinkedList<String> readDictionary(String path, String name) {
        Yaml yaml = new Yaml();
        HashMap<Integer, List<String>> dictionarySet;

        try {
            InputStream inputStream = new FileInputStream(new File(path));
            dictionarySet = yaml.load(inputStream);

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] readDictionary : " + e.getMessage());
            return null;
        }

        LinkedList<String> dictionary = new LinkedList<>();
        dictionary.addAll(dictionarySet.get(name));

        return dictionary;
    }
}
