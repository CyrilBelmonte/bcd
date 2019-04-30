package com.ucp.scrapper.test;

import com.ucp.scrapper.engine.ScrapToBd;
public class Test {
    public static void main(String[] args) {
        ScrapToBd scrapToBd = new ScrapToBd();
        int test = 100;
        scrapToBd.execute(test);
    }
}