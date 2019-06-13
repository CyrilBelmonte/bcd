package com.ucp.xml.parse_xml.parser_csv.dao.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HelperTest{
    private final static String FILE_NAME ="src/main/resources/test.csv";

    public static void main(String[] args) {
        final String fileName =  FILE_NAME;

        List<String> test ;

        try {
            final File file = CsvFileHelper.getRessource(fileName);
            test = CsvFileHelper.readFile(file);
            System.out.println(test.toString());
        }catch (Exception e){
           System.err.println("Error :"+e);
        }

    }

}
