package com.ucp.scraper_updated.demo;

import com.ucp.scraper_updated.engine.ScrapeToDatabase;


public class ScraperDemo {
    public static void main(String[] args) {
        ScrapeToDatabase scrapeToDatabase = new ScrapeToDatabase();
        scrapeToDatabase.execute(100);
    }
}
