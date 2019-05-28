package com.ucp.xml.parse_xml.parser_csv.dao.csv;

import java.io.File;
import java.util.List;

public interface CsvFileInt {
    File getFile();
    List<String []> getData();
}
