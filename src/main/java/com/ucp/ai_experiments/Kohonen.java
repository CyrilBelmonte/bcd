package com.ucp.ai_experiments;

import com.ucp.cookwithease.model.Recipe;

import java.util.LinkedList;


public class Kohonen {
    private LinkedList<AINeuron> neurons = new LinkedList<>();
    private LinkedList<AIEntry> entries = new LinkedList<>();

    private LinkedList<Integer> unpickedEntries = new LinkedList<>();
    private LinkedList<String> coordinatesNames = new LinkedList<>();

    private int maxNeurons = 0;
    private int iterations = 0;
    private double epsilon = 0.0;
    private double neighboringPerimeter = 0.0;

    private void initializeObjects(LinkedList<Recipe> recipes) {
        AINeuron neuron;
        AIEntry entry;

        LinkedList<String> allIngredientsName = AIEntries.getAllMainCoursesIngredients();
        LinkedList<String> allRecipesName = AIEntries.getPartsOfMainCoursesName();

        coordinatesNames.addAll(allIngredientsName);
        coordinatesNames.addAll(allRecipesName);

        for (int i = 0; i < maxNeurons; i++) {
            neuron = new AINeuron();
            neuron.setId(i);
            neuron.initializeCoordinates(allIngredientsName, allRecipesName);

            neurons.addLast(neuron);
        }

        for (Recipe recipe : recipes) {
            entry = new AIEntry();
            entry.setRecipe(recipe);
            entry.initializeCoordinates(allIngredientsName, allRecipesName);

            entries.addLast(entry);
        }
    }

    private AIEntry pickEntry() {
        if (unpickedEntries.size() == 0) {
            for (int i = 0; i < entries.size(); i++) {
                unpickedEntries.addLast(i);
            }
        }

        int pickedIndex = (int) (Math.random() * (unpickedEntries.size() - 1));
        unpickedEntries.remove(pickedIndex);

        return entries.get(pickedIndex);
    }

    private AINeuron getWinner(AIEntry entry) {
        AINeuron winnerNeuron = null;
        double winnerActivity = 0.0;
        double neuronActivity;

        for (AINeuron neuron : neurons) {
            neuronActivity = neuron.calculateActivity(entry);

            if (neuronActivity > winnerActivity) {
                winnerNeuron = neuron;
                winnerActivity = neuronActivity;
            }
        }

        return winnerNeuron;
    }

    private double neighbourhoodFunction(double coordinateA, double coordinateB) {
        double distanceBetweenCoordinates = Math.abs(coordinateA - coordinateB);

        if (distanceBetweenCoordinates > neighboringPerimeter) {
            return 0.0;

        } else {
            return 1 - distanceBetweenCoordinates / neighboringPerimeter;
        }
    }

    private void updateAllCoordinates(AINeuron winnerNeuron, AIEntry entry) {
        double entryCoordinate;
        double neuronCoordinate;
        double newCoordinate;

        for (AINeuron neuron : neurons) {
            for (String coordinateName : coordinatesNames) {
                entryCoordinate = entry.getCoordinateValue(coordinateName);
                neuronCoordinate = neuron.getCoordinateValue(coordinateName);

                newCoordinate = epsilon * (entryCoordinate - neuronCoordinate);

                if (neuron != winnerNeuron) {
                    newCoordinate *= neighbourhoodFunction(neuronCoordinate, entryCoordinate);
                }

                neuron.setCoordinateValue(coordinateName, newCoordinate);
            }
        }
    }

    private LinkedList<Cluster> clusterize() {
        double distance;
        double minDistance;
        AINeuron nearestNeuron;

        Cluster cluster;
        LinkedList<Cluster> clusters = new LinkedList<>();

        for (int i = 0; i < maxNeurons; i++) {
            clusters.addLast(new Cluster(i));
        }

        for (AIEntry entry : entries) {
            minDistance = 1000000000.0;
            nearestNeuron = null;

            for (AINeuron neuron : neurons) {
                distance = entry.distanceTo(neuron);

                if (distance < minDistance) {
                    nearestNeuron = neuron;
                    minDistance = distance;
                }
            }

            cluster = clusters.get(nearestNeuron.getId());
            cluster.copyCoordinatesFrom(nearestNeuron);
            cluster.addEntry(entry);
        }

        int i = 0;

        while (i < clusters.size()) {
            cluster = clusters.get(i);

            if (cluster.isEmpty()) {
                clusters.remove(i);

            } else {
                i++;
            }
        }

        return clusters;
    }

    public void execute() {
        AIEntry pickedEntry;
        AINeuron winnerNeuron;

        for (int i = 0; i < iterations; i++) {
            for (AIEntry entry : entries) {
                pickedEntry = pickEntry();
                winnerNeuron = getWinner(pickedEntry);
                updateAllCoordinates(winnerNeuron, pickedEntry);
            }

            System.out.print("\rLearning... " + ((double) Math.round((double) i / iterations * 1000) / 10) + " %");
        }

        System.out.println("\n");

        LinkedList<Cluster> clusters = clusterize();

        for (Cluster cluster : clusters) {
            System.out.println(cluster);
        }
    }

    public void setEntries(LinkedList<Recipe> recipes) {
        initializeObjects(recipes);
    }

    public void setMaxNeurons(int maxNeurons) {
        this.maxNeurons = maxNeurons;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public void setNeighboringPerimeter(double neighboringPerimeter) {
        this.neighboringPerimeter = neighboringPerimeter;
    }
}
