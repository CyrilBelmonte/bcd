package com.ucp.ai_experiments;

import com.ucp.cookwithease.dao.DAOFactory;


public class AIDemo {
    public static void main(String[] args) {
        Kohonen kohonen = new Kohonen();
        kohonen.setMaxNeurons(30);
        kohonen.setIterations(1000);
        kohonen.setEpsilon(0.5);
        kohonen.setNeighboringPerimeter(2);
        kohonen.setEntries(DAOFactory.getRecipeDAO().findAllMainCourses());
        kohonen.execute();
    }
}
