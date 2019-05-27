package com.ucp.scraper_updated.preprocessing;

import com.ucp.scraper_updated.preprocessing.csv.ReaderCsv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Url2Array {
    private static final String FILE_NAME ="src/main/resources/urlsBon.csv";
    private static File file = new File(FILE_NAME);
    private static ReaderCsv readerCsv;

    public static void main(String[] args) {
        File file = new File(FILE_NAME);
        readerCsv = new ReaderCsv(file);

        List<String > urls = new ArrayList<>();
        urls = readerCsv.reader();




        for(int index = 0 ; index<urls.size();index++) {
            System.out.println("Index = " + index + " url : " + urls.get(index).toString());
        }
        System.out.println("Size "+urls.size());
    }


}
