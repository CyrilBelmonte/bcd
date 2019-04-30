package com.ucp.scraper.tests;

import com.ucp.scraper.engine.ScrapToBd;
public class Test {
    public static void main(String[] args) {
        ScrapToBd scrapToBd = new ScrapToBd();
        int test = 100;
        scrapToBd.execute(test);
    }
}