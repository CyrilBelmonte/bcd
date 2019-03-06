package com.ucp.scrapper.engine;

import com.ucp.scrapper.data.WebConnection.WebConnection;
import lombok.Data;
import org.jsoup.nodes.Document;


@Data
public class Scrapper {

    private String url;

    private WebConnection webConnection;

    private Document documentWeb;

    public Scrapper(String url) {
        try {
            this.url = url;
            this.webConnection = new WebConnection(url);
            this.documentWeb = webConnection.getConnection().execute().parse();

        } catch (Exception e) {
            System.err.println("[ERROR] " + e);
        }
    }
}
