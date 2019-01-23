package com.ucp.scrapper.Data.RecipeData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private int ingQuantities;
    private String units;
    private String name;
    private String url;
}
