package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Recipe recipe;

    private String description;
    private int rating;
    private Date publicationDate;
}
