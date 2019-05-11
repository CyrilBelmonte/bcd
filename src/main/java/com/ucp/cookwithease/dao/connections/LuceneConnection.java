package com.ucp.cookwithease.dao.connections;

import com.ucp.cookwithease.dao.lucene.LuceneIndex;

import java.nio.file.Path;
import java.nio.file.Paths;


public class LuceneConnection {
    private static LuceneIndex luceneIndex;

    private LuceneConnection() {}

    public static LuceneIndex getConnection() {
        if (luceneIndex == null) {
            Path indexPath = Paths.get(LuceneConfiguration.INDEX_LOCATION);
            luceneIndex = new LuceneIndex(indexPath);
        }

        return luceneIndex;
    }
}
