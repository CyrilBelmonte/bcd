package com.ucp.cookwithease.dao.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class LuceneIndexer {
    private Path indexPath;
    private IndexWriter writer;

    public LuceneIndexer(Path indexPath) {
        this.indexPath = indexPath;
    }

    private boolean openIndex(OpenMode mode) {
        if (!Files.isDirectory(indexPath)) {
            try {
                Files.createDirectories(indexPath);

            } catch (IOException e) {
                // Nothing
            }
        }

        if (!Files.isReadable(indexPath)) {
            System.err.println("[ERROR] Lucene index "
                + indexPath.toString()
                + " does not exists or is not readable.");

            return false;
        }

        try {
            Directory directory = FSDirectory.open(indexPath);
            Analyzer analyzer = new FrenchAnalyzer();
            IndexWriterConfig indexConfiguration = new IndexWriterConfig(analyzer);

            indexConfiguration.setOpenMode(mode);

            writer = new IndexWriter(directory, indexConfiguration);

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean openIndex() {
        return openIndex(OpenMode.APPEND);
    }

    public boolean createIndex() {
        return openIndex(OpenMode.CREATE);
    }

    public boolean indexDocument(Document document) {
        try {
            writer.addDocument(document);
            return true;

        } catch (IOException e) {
            System.err.println("[ERROR] The document cannot be indexed.");
            return false;
        }
    }

    public boolean close() {
        try {
            writer.close();
            return true;

        } catch (IOException e) {
            System.err.println("[ERROR] The writer cannot be closed.");
            return false;
        }
    }
}
