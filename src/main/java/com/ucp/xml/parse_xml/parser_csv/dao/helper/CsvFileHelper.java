package com.ucp.xml.parse_xml.parser_csv.dao.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class CsvFileHelper{
    public static String getRessourcePath(String fileName){
        final File f = new File("");
        final String directoryPath = f.getAbsolutePath() + File.separator + fileName;
        return directoryPath;
    }

    public static File getRessource(String fileName){
        final String completeFileName = getRessourcePath((fileName));
        File file = new File(completeFileName);
        return file;
    }

    public static List<String> readFile(File file){

        List<String> result = new ArrayList<String>();

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            for(String line = br.readLine();line != null;line = br.readLine()){
                result.add(line);
            }
            br.close();
            fr.close();
        }catch (IOException ex){
            System.err.println("Error : "+ex);
        }

        return result;


    }
}
