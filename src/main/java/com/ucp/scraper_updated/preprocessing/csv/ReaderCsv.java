package com.ucp.scraper_updated.preprocessing.csv;

import java.io.*;
import java.util.ArrayList;

public class ReaderCsv {
    private File file;
    private ArrayList<String > urls = new ArrayList<String>();
    public ReaderCsv(File file){
        this.file=file;
    }

    public ArrayList<String> reader(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = reader.readLine()) != null){
                urls.add(line.replace(';',' '));
            }
            reader.close();

        }catch (FileNotFoundException ex){
            System.err.println("[ERROR] File = "+ex.toString());
        }catch (IOException ex ){
            System.err.println("[ERROR] IOException = "+ex.toString());
        }
        return urls;

    }
}
