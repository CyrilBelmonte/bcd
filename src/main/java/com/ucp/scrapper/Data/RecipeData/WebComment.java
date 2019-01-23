package com.ucp.scrapper.Data.RecipeData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebComment {
    private String comment;
    private int mark;
    private String pseudo;
}