package com.ucp.scraper.preprocessing.urlscrape;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class ScrapeMain {
    public static void main(String[] args) throws Exception{

        ArrayList<String> urlsGl = new ArrayList<String>();
        ArrayList<String> errsGL = new ArrayList<String>();

        ArrayList<String> urls1 = new ArrayList<String>();
        ArrayList<String> urls2 = new ArrayList<String>();
        ArrayList<String> urls3 = new ArrayList<String>();
        ArrayList<String> urls4 = new ArrayList<String>();

        ArrayList<String> urls5 = new ArrayList<String>();
        ArrayList<String> urls6 = new ArrayList<String>();
        ArrayList<String> urls7 = new ArrayList<String>();
        ArrayList<String> urls8 = new ArrayList<String>();

        ArrayList<String> urls9 = new ArrayList<String>();
        ArrayList<String> urls10 = new ArrayList<String>();
        ArrayList<String> urls11 = new ArrayList<String>();
        ArrayList<String> urls12 = new ArrayList<String>();

        ArrayList<String> urls13 = new ArrayList<String>();
        ArrayList<String> urls14 = new ArrayList<String>();
        ArrayList<String> urls15 = new ArrayList<String>();
        ArrayList<String> urls16 = new ArrayList<String>();

        ArrayList<String> err1 = new ArrayList<String>();
        ArrayList<String> err2 = new ArrayList<String>();
        ArrayList<String> err3 = new ArrayList<String>();
        ArrayList<String> err4 = new ArrayList<String>();

        ArrayList<String> err5 = new ArrayList<String>();
        ArrayList<String> err6 = new ArrayList<String>();
        ArrayList<String> err7 = new ArrayList<String>();
        ArrayList<String> err8 = new ArrayList<String>();

        ArrayList<String> err9 = new ArrayList<String>();
        ArrayList<String> err10 = new ArrayList<String>();
        ArrayList<String> err11 = new ArrayList<String>();
        ArrayList<String> err12 = new ArrayList<String>();

        ArrayList<String> err13 = new ArrayList<String>();
        ArrayList<String> err14 = new ArrayList<String>();
        ArrayList<String> err15 = new ArrayList<String>();
        ArrayList<String> err16 = new ArrayList<String>();

        ScrapeThread scrappeThread1 = new ScrapeThread(0,25000,urls1,err1,1);
        ScrapeThread scrappeThread2 = new ScrapeThread(25000,50000,urls2,err2,2);
        ScrapeThread scrappeThread3 = new ScrapeThread(50000,75000,urls3,err3,3);
        ScrapeThread scrappeThread4 = new ScrapeThread(75000,100000,urls4,err4,4);

        ScrapeThread scrappeThread12 = new ScrapeThread(100000,125000,urls5,err5,5);
        ScrapeThread scrappeThread22 = new ScrapeThread(125000,150000,urls6,err6,6);
        ScrapeThread scrappeThread32 = new ScrapeThread(150000,175000,urls7,err7,7);
        ScrapeThread scrappeThread42 = new ScrapeThread(175000,200000,urls8,err8,8);

        ScrapeThread scrappeThread13 = new ScrapeThread(200000,225000,urls9,err9,9);
        ScrapeThread scrappeThread23 = new ScrapeThread(225000,250000,urls10,err10,10);
        ScrapeThread scrappeThread33 = new ScrapeThread(250000,275000,urls11,err11,11);
        ScrapeThread scrappeThread43 = new ScrapeThread(275000,200000,urls12,err12,12);

        ScrapeThread scrappeThread14 = new ScrapeThread(300000,325000,urls13,err13,13);
        ScrapeThread scrappeThread24 = new ScrapeThread(325000,350000,urls14,err14,14);
        ScrapeThread scrappeThread34 = new ScrapeThread(350000,375000,urls15,err15,15);
        ScrapeThread scrappeThread44 = new ScrapeThread(375000,400000,urls16,err16,16);

        scrappeThread1.start();
        scrappeThread2.start();
        scrappeThread3.start();
        scrappeThread4.start();
        scrappeThread12.start();
        scrappeThread22.start();
        scrappeThread32.start();
        scrappeThread42.start();
        scrappeThread13.start();
        scrappeThread23.start();
        scrappeThread33.start();
        scrappeThread43.start();
        scrappeThread14.start();
        scrappeThread24.start();
        scrappeThread34.start();
        scrappeThread44.start();

        scrappeThread1.join();
        scrappeThread2.join();
        scrappeThread3.join();
        scrappeThread4.join();
        scrappeThread12.join();
        scrappeThread22.join();
        scrappeThread32.join();
        scrappeThread42.join();
        scrappeThread13.join();
        scrappeThread23.join();
        scrappeThread33.join();
        scrappeThread43.join();
        scrappeThread14.join();
        scrappeThread24.join();
        scrappeThread34.join();
        scrappeThread44.join();

        urlsGl.addAll(scrappeThread1.getUrls());
        urlsGl.addAll(scrappeThread2.getUrls());
        urlsGl.addAll(scrappeThread3.getUrls());
        urlsGl.addAll(scrappeThread4.getUrls());
        urlsGl.addAll(scrappeThread12.getUrls());
        urlsGl.addAll(scrappeThread22.getUrls());
        urlsGl.addAll(scrappeThread32.getUrls());
        urlsGl.addAll(scrappeThread42.getUrls());
        urlsGl.addAll(scrappeThread13.getUrls());
        urlsGl.addAll(scrappeThread23.getUrls());
        urlsGl.addAll(scrappeThread33.getUrls());
        urlsGl.addAll(scrappeThread43.getUrls());
        urlsGl.addAll(scrappeThread14.getUrls());
        urlsGl.addAll(scrappeThread24.getUrls());
        urlsGl.addAll(scrappeThread34.getUrls());
        urlsGl.addAll(scrappeThread44.getUrls());

        errsGL.addAll(scrappeThread1.getErr());
        errsGL.addAll(scrappeThread2.getErr());
        errsGL.addAll(scrappeThread3.getErr());
        errsGL.addAll(scrappeThread4.getErr());
        errsGL.addAll(scrappeThread12.getErr());
        errsGL.addAll(scrappeThread22.getErr());
        errsGL.addAll(scrappeThread32.getErr());
        errsGL.addAll(scrappeThread42.getErr());
        errsGL.addAll(scrappeThread13.getErr());
        errsGL.addAll(scrappeThread23.getErr());
        errsGL.addAll(scrappeThread33.getErr());
        errsGL.addAll(scrappeThread43.getErr());
        errsGL.addAll(scrappeThread14.getErr());
        errsGL.addAll(scrappeThread24.getErr());
        errsGL.addAll(scrappeThread34.getErr());
        errsGL.addAll(scrappeThread44.getErr());

        System.out.println("FINAL  Bon  = "+urlsGl.size());
        System.out.println("FINAL  Faux  = "+errsGL.size());
        String str = "./src/main/resources/urlsBon.csv";
        String str2 = "./src/main/resources/urlsFaux.csv";

        try{
            File ff = new File(str);
            ff.createNewFile();
            FileWriter ffwriter = new FileWriter(ff);
            for(int index = 0;index<urlsGl.size();index++ ){
                ffwriter.write(urlsGl.get(index)+";\n");
            }
            ffwriter.close();
        }catch (Exception e){
            System.err.println("Error 2"+e);
        }
        try{
            File ff1 = new File(str2);
            ff1.createNewFile();
            FileWriter ff2writer = new FileWriter(ff1);
            for(int index2 = 0;index2<errsGL.size();index2++ ){
                ff2writer.write(errsGL.get(index2)+";\n");
            }
            ff2writer.close();
        }catch (Exception e){
            System.err.println("Error 1"+e);
        }

    }
}
