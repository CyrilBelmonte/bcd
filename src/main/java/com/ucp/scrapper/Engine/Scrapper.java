package com.ucp.scrapper.Engine;

import com.ucp.scrapper.Data.WebConnection.WebConnection;
import lombok.Data;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

@Data
public class Scrapper {

    Logger logger = Logger.getLogger("Scrapper");

    private String url;

    private WebConnection webConnection;

    private Document documentWeb;

    public Scrapper(String url) {
        try {
            this.url = url;
            this.webConnection = new WebConnection(url);
            this.documentWeb = webConnection.getConnection().get();

        } catch (Exception e) {
            logger.error(this.getClass().getName() + " " + e);
        }
    }
}
