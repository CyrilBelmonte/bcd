package com.ucp.scrapper.data.RecipeData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    private String description;
    private int stepNumber;
}
