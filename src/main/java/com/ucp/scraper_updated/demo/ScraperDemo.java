package com.ucp.scraper_updated.demo;

import com.ucp.scraper_updated.engine.ScrapeToDatabase;
import com.ucp.scraper_updated.preprocessing.csv.ReaderCsv;

import java.io.File;
import java.util.ArrayList;


public class ScraperDemo {
    private static ArrayList<String> urls;
    private static final String FILE_NAME ="src/main/resources/urlsBon.csv";
    private static File file = new File(FILE_NAME);

    public static void main(String[] args) {
        ReaderCsv readerCsv = new ReaderCsv(file);
        ScrapeToDatabase scrapeToDatabase = new ScrapeToDatabase();
        scrapeToDatabase.execute(1000, readerCsv.reader());
    }
}
