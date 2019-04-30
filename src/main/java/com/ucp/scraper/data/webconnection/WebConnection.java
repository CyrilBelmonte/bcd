package com.ucp.scraper.data.webconnection;

import lombok.Data;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

@Data
public class WebConnection {

    Logger logger = Logger.getLogger("webconnection");

    private String url;
    private Connection connection;

    public WebConnection(String url) {
        try {
            this.url = url;
            this.connection = Jsoup.connect(url);
            this.connection.followRedirects(true);
            this.connection.execute();
        } catch (Exception e) {
            logger.error(this.getClass().getName() + " " + e);
        }

    }
}
