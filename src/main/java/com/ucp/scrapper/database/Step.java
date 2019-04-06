package com.ucp.scrapper.database;

public class Step {
	private String id;
	private int stepNumber;
	private String instructions;
	private String idRecipe;
	
	public Step(int stepNumber, String instructions, String idRecipe) {
		this.stepNumber = stepNumber;
		this.instructions = instructions;
		this.idRecipe = idRecipe;
	}
	public String getId() {
		return id;
	}
	public int getStepNumber() {
		return stepNumber;
	}
	public String getInstructions() {
		return instructions;
	}
	public String getIdRecipe() {
		return idRecipe;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public void setIdRecipe(String idRecipe) {
		this.idRecipe = idRecipe;
	}
	@Override
	public String toString() {
		return "Steps [id=" + id + ", stepNumber=" + stepNumber + ", instructions=" + instructions + "]";
	}
	
}
