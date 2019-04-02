package jdbc;

public class Recipe {
	private String id;
	private String mark;
	private Ingredient ingredient;
	private Comment comment;
	private Step step;
	private int cookingTime;
	private int preparationTime;
	private int recipeTime;
	
	
	public Recipe() {
	}

	public Recipe(String mark, Ingredient ingredient, Comment comment, Step step, int cookingTime,
			int preparationTime, int recipeTime) {
		this.mark = mark;
		this.ingredient = ingredient;
		this.comment = comment;
		this.step = step;
		this.cookingTime = cookingTime;
		this.preparationTime = preparationTime;
		this.recipeTime = recipeTime;
	}

	public String getId() {
		return id;
	}

	public String getMark() {
		return mark;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public Comment getComment() {
		return comment;
	}

	public Step getStep() {
		return step;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void setStep(Step step) {
		this.step = step;
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

	@Override
	public String toString() {
		return "Recipes [id=" + id + ", mark=" + mark + ", ingredient=" + ingredient + ", comment=" + comment
				+ ", step=" + step + ", cookingTime=" + cookingTime + ", preparationTime=" + preparationTime
				+ ", recipeTime=" + recipeTime + "]";
	}
	
}
