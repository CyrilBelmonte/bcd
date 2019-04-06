package com.ucp.scrapper.database;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String id;
	private String description;
	private List<Recipe> recipes = new ArrayList<Recipe>();
	
	public Category(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public Recipe getRecipe(int index) {
		return recipes.get(index);
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
	public void removeRecipe(int index) {
		recipes.remove(index);
	}
	@Override
	public String toString() {
		return "Categories [id=" + id + ", description=" + description + ", recipe=" + recipes + "]";
	}
	
}
