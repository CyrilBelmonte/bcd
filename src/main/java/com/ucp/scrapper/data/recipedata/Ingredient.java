package com.ucp.scrapper.data.recipedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private float ingQuantities;
    private String units;
    private String name;
    private String url;
}
