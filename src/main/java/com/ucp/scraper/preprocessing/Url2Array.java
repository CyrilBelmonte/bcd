package com.ucp.scraper.preprocessing;

import com.ucp.scraper.preprocessing.csv.ReaderCsv;

import java.io.File;
import java.util.List;


public class Url2Array {

    public static void main(String[] args) {

        String FILE_NAME = "src/main/resources/urlsBon.csv";
        ReaderCsv readerCsv;
        File file = new File(FILE_NAME);
        readerCsv = new ReaderCsv(file);

        List<String> urls;
        urls = readerCsv.reader();

        for (int index = 0; index < urls.size(); index++) {
            System.out.println("Index : " + index + " url : " + urls.get(index));
        }
        System.out.println("Number of url : " + urls.size());
    }
}
