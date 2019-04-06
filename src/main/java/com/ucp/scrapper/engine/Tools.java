package com.ucp.scrapper.engine;

public class Tools {

    public int parseTime(String timeInStr) {
        if (timeInStr.contains("min")) {
            return ((Integer.parseInt(timeInStr.replaceAll(" min", ""))));
        } else {
            if (timeInStr.contains("h")) {
                String[] timeStrT = timeInStr.split("h");
                int tmpH = 0;
                for (int i = 0; i < timeStrT.length; i++) {
                    if (i == 0) {
                        tmpH = tmpH + Integer.parseInt(timeStrT[i].replaceAll(" ", "")) * 60;
                    } else {
                        tmpH = tmpH + Integer.parseInt(timeStrT[i].replaceAll(" ", ""));
                    }
                }
                return tmpH;
            } else {
                return 0;
            }
        }
    }

    public String ingredient(String chaine) {
        if (chaine.contains("g "))
            return chaine.replaceAll("g ", "");
        else if (chaine.contains("l "))
            return chaine.replaceAll("l ", "");
        else if (chaine.contains("cl "))
            return chaine.replaceAll("cl ", "");
        else if (chaine.contains("kg "))
            return chaine.replaceAll("kg ", "");
        else
            return chaine;
    }

    public String unit(String chaine) {
        if (chaine.contains("g "))
            return "g";
        else if (chaine.contains("l "))
            return "l";
        else if (chaine.contains("cl "))
            return "cl";
        else if (chaine.contains("kg "))
            return "kg";
        else
            return "unite";
    }
}
