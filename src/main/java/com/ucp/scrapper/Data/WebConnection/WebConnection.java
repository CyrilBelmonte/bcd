package com.ucp.scrapper.Data.WebConnection;

import lombok.Data;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

@Data
public class WebConnection {

    Logger logger = Logger.getLogger("WebConnection");
    private String url;
    private Connection connection;

    public WebConnection(String url) throws Exception {
        try {
            this.url = url;
            this.connection = Jsoup.connect(url);
            this.connection.followRedirects(true);
        } catch (Exception e) {
            logger.error(this.getClass().getName() + " " + e);
        }

    }
}
