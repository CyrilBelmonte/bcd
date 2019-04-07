package com.ucp.scrapper.database;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	private String id;
	private float mark;
	private int cookingTime;
	private int preparationTime;
	private int recipeTime;
	private int numberPersons;
	private String title;
	private String difficulty;
	private String economicLevel;
	private String picture;
	
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private List<Step> steps = new ArrayList<Step>();
	private List<Comment> comments = new ArrayList<Comment>();
	
	private Category category;
	
	public Recipe() {
	}

	public Recipe(float mark, int cookingTime, int preparationTime, int recipeTime, int numberPersons,
			String title, String difficulty, String economicLevel, String picture, List<Ingredient> ingredients,
			List<Step> steps, Category category) {
		this.mark = mark;
		this.cookingTime = cookingTime;
		this.preparationTime = preparationTime;
		this.recipeTime = recipeTime;
		this.numberPersons = numberPersons;
		this.title = title;
		this.difficulty = difficulty;
		this.economicLevel = economicLevel;
		this.picture = picture;
		this.ingredients = ingredients;
		this.steps = steps;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public float getMark() {
		return mark;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public int getPreparationTime() {
		return preparationTime;
	}

	public int getRecipeTime() {
		return recipeTime;
	}

	public int getNumberPersons() {
		return numberPersons;
	}

	public String getTitle() {
		return title;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public String getEconomicLevel() {
		return economicLevel;
	}

	public String getPicture() {
		return picture;
	}

	public Category getCategory() {
		return category;
	}

	public Ingredient getIngredients(int index) {
		return ingredients.get(index);
	}

	public Step getSteps(int index) {
		return steps.get(index);
	}

	public Comment getComments(int index) {
		return comments.get(index);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	public void setRecipeTime(int recipeTime) {
		this.recipeTime = recipeTime;
	}

	public void setNumberPersons(int numberPersons) {
		this.numberPersons = numberPersons;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public void setEconomicLevel(String economicLevel) {
		this.economicLevel = economicLevel;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public void addIngredients(Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	public void addSteps(Step step) {
		steps.add(step);
	}

	public void addComments(Comment comment) {
		comments.add(comment);
	}
	
	public void removeIngredients(int index) {
		ingredients.remove(index);
	}

	public void removeSteps(int index) {
		steps.remove(index);
	}

	public int SizeSteps() {  return steps.size(); }

	public void removeComments(int index) {
		comments.remove(index);
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", mark=" + mark + ", cookingTime=" + cookingTime + ", preparationTime="
				+ preparationTime + ", recipeTime=" + recipeTime + ", numberPersons=" + numberPersons + ", title="
				+ title + ", difficulty=" + difficulty + ", economicLevel=" + economicLevel + ", picture=" + picture
				+ ", ingredients=" + ingredients + ", steps=" + steps + ", comments=" + comments + "]";
	}	
}
