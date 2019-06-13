package com.ucp.scraper.engine;

import lombok.Data;
import org.jsoup.nodes.Document;

import java.io.IOException;


@Data
public class Scraper {
    private String url;
    private WebConnection webConnection;
    private Document webDocument;

    public Scraper(String url) {
        this.url = url;

        try {
            webConnection = new WebConnection(url);
            webDocument = webConnection.getConnection().execute().parse();

        } catch (IOException e) {
            System.err.println("[ERROR] " +
                this.getClass().getName() + " : " + e.getMessage());
        }
    }
}
