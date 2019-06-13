package com.ucp.scraper.engine;

import lombok.Data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;


@Data
public class WebConnection {
    private String url;
    private Connection connection;

    public WebConnection(String url) {
        this.url = url;

        try {
            connection = Jsoup.connect(url);
            connection.followRedirects(true);
            connection.execute();

        } catch (IOException e) {
            System.err.println("[ERROR] " +
                this.getClass().getName() + " : " + e.getMessage());
        }
    }
}
