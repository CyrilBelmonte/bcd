package jdbc;

public class Category {
	private String id;
	private String description;
	private Recipe recipe;
	
	public Category(String description, Recipe recipe) {
		this.description = description;
		this.recipe = recipe;
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@Override
	public String toString() {
		return "Categories [id=" + id + ", description=" + description + ", recipe=" + recipe + "]";
	}
	
}
