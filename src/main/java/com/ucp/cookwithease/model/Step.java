package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    private Recipe recipe;

    private int id;
    private int position;
    private String description;
}
