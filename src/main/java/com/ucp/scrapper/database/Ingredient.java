package com.ucp.scrapper.database;

public class Ingredient {
	private String id;
	private int quantities;
	private String name;
	private String url;
	private String idRecipe;
	
	public Ingredient() {
		
	}
	public Ingredient(int quantities, String name, String idRecipe, String url) {
		this.quantities = quantities;
		this.name = name;
		this.url = url;
		this.idRecipe = idRecipe;
	}
	public String getId() {
		return id;
	}
	public int getQuantities() {
		return quantities;
	}
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public String getIdRecipe() {
		return idRecipe;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIdRecipe(String idRecipe) {
		this.idRecipe = idRecipe;
	}
	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", quantities=" + quantities + ", name=" + name + " url=" + url + "]";
	}

	

	
}
