package com.ucp.ai_experiments;

import java.util.LinkedList;


public class Cluster extends AIObject {
    private int id;
    private LinkedList<AIEntry> entries = new LinkedList<>();

    public Cluster(int id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return entries.size() == 0;
    }

    public LinkedList<AIEntry> getEntries() {
        return entries;
    }

    public int getId() {
        return id;
    }

    public void addEntry(AIEntry entry) {
        entries.addLast(entry);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuffer clusterDescription = new StringBuffer();

        for (AIEntry entry : entries) {
            clusterDescription.append(entry.getRecipe().getName());
            clusterDescription.append("\n");
        }

        return "*** CLUSTER " + id + " ***\n" + clusterDescription + "\n";
    }
}
