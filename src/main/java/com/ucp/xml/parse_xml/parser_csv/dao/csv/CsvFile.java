package com.ucp.xml.parse_xml.parser_csv.dao.csv;


import com.ucp.xml.parse_xml.parser_csv.dao.helper.CsvFileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CsvFile implements CsvFileInt{
    public final static char SEPARATOR = ';' ;

    private File file;
    private List<String > lines;
    private List<String[] > data;

    private CsvFile(){

    }
    public CsvFile(File file){
        this.file= file;

        init();
    }
    private void init(){
        lines = CsvFileHelper.readFile(file);

        data = new ArrayList<String[]> (lines.size());

        String sep = new Character(SEPARATOR).toString();
        for (String line : lines){
            String[] oneData = line.split(sep);
            data.add(oneData);
        }
    }

    public File getFile() {
        return file;
    }

    public List<String> getLines() {
        return lines;
    }

    public List<String[]> getData() {
        return data;
    }
}
