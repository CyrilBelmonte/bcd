
package com.ucp.ia;

import com.ucp.cookwithease.model.*;


public class IngredientsWeight {
    private Double Weight;
    private String Name;

    public IngredientsWeight(String name) {
        Weight = 0.0;
        Name = name;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



}

