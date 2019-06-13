package com.ucp.scraper.preprocessing.urlscrape;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.ArrayList;

public class Scrape {
    private String url;
    private Connection connection;

    public String getTitle() {
        return title;
    }

    private String title;
    private ArrayList<String> err;

    public Scrape(String url, ArrayList<String> err) {
        this.err = err;
        this.url = url;
        try {
            this.connection = Jsoup.connect(url);
            this.connection.followRedirects(true);
            this.connection.execute();
            this.title = getTitleFromWeb();
        } catch (Exception e) {
            err.add(url);
            System.err.println(url);
        }
    }

    private String getTitleFromWeb() {
        try {
            return this.connection.get().select("#content > .padded-content > .main-title").get(0).ownText();
        } catch (Exception e) {
            return null;
        }
    }
}
