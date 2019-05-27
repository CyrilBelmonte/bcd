package com.ucp.cookwithease.model;

import com.ucp.cookwithease.tools.Tools;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    // Used by the database
    private int id;
    private int recipeID;

    private String name;
    private String cleanedName;
    private float quantity;
    private String unit;

    @Override
    public String toString() {
        String unitDescription = (unit != null) ? " " + unit : "";
        String quantityDescription = (quantity > 0) ? "(" + quantity + unitDescription + ")" : "";

        return "    - " + Tools.capitalize(name) + " " + quantityDescription;
    }
}
