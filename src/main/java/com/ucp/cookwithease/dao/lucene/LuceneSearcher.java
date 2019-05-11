package com.ucp.cookwithease.dao.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;


public class LuceneSearcher {
    private Path indexPath;
    private IndexSearcher searcher;

    public LuceneSearcher(Path indexPath) {
        this.indexPath = indexPath;
    }

    private boolean initSearcher() {
        if (!Files.isReadable(indexPath)) {
            System.err.println("[ERROR] Lucene index "
                + indexPath.toString()
                + " does not exists or is not readable.");

            return false;
        }

        try {
            Directory directory = FSDirectory.open(indexPath);
            IndexReader reader = DirectoryReader.open(directory);
            searcher = new IndexSearcher(reader);

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    private IndexSearcher getSearcher() {
        if (searcher == null) {
            initSearcher();
        }

        return searcher;
    }

    public LinkedList<Document> search(String keywords, int maxResults, String... fields) {
        LinkedList<Document> documents = new LinkedList<>();

        if (keywords.isEmpty()) {
            return documents;
        }

        try {
            Analyzer analyzer = new StandardAnalyzer();
            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);

            Query parsedQuery = queryParser.parse(keywords);
            ScoreDoc[] results = getSearcher().search(parsedQuery, maxResults).scoreDocs;

            for (ScoreDoc result : results) {
                Document document = searcher.doc(result.doc);
                documents.addLast(document);
            }

        } catch (ParseException | IOException e) {
            System.err.println("[ERROR] The Lucene query "
                + keywords + " cannot be processed.");
        }

        return documents;
    }
}
