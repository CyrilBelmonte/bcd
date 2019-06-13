package com.ucp.scraper.demo;

import com.ucp.scraper.engine.ScrapeToDatabase;
import com.ucp.scraper.preprocessing.csv.ReaderCsv;

import java.io.File;


public class ScraperDemo {
    private static final String FILE_NAME ="src/main/resources/urlsBon.csv";
    private static File file = new File(FILE_NAME);

    public static void main(String[] args) {
        ReaderCsv readerCsv = new ReaderCsv(file);
        ScrapeToDatabase scrapeToDatabase = new ScrapeToDatabase();
        scrapeToDatabase.execute(1000, readerCsv.reader());
    }
}
