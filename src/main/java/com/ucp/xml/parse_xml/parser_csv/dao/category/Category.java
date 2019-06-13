package com.ucp.xml.parse_xml.parser_csv.dao.category;

import java.util.HashMap;

public interface Category {
    HashMap<Integer, Float> getCatDist();

    HashMap<Integer, Float> getRecDist();

    String getIdOfCat();

    String getTypeOfCat();
}
