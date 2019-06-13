package com.ucp.scraper.preprocessing.urlscrape;

import java.util.ArrayList;

public class ScrapeThread extends Thread {

    private int depart;
    private int fin;
    private int id;
    private ArrayList<String> urls;
    private ArrayList<String> err;

    public ArrayList<String> getUrls() {
        return urls;
    }

    public ArrayList<String> getErr() {
        return err;
    }

    public ScrapeThread(int depart, int fin, ArrayList<String> urls, ArrayList<String> err, int id) {
        this.depart = depart;
        this.fin = fin;
        this.urls = urls;
        this.id = id;
        this.err = err;
    }

    public void run() {
        int total = fin - depart;
        while (depart <= fin) {
            String url = "https://www.marmiton.org/recettes/recette_*_" + depart + ".aspx";
            Scrape scrape = new Scrape(url, err);
            if (scrape.getTitle() != null) {
                float percent_dec = (total / depart) * 100;
                urls.add(url);
                System.out.println("[THREAD " + id + " DEC :" + percent_dec + "% ADD ] : " + scrape.getTitle());
            }
            depart++;
        }
    }
}
