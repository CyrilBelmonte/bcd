package jdbc;

public class Ingredient {
	private String id;
	private int quantities;
	private String name;
	private String idRecipe;
	
	public Ingredient(int quantities, String name, String idRecipe) {
		this.quantities = quantities;
		this.name = name;
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
	public void setIdRecipe(String idRecipe) {
		this.idRecipe = idRecipe;
	}
	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", quantities=" + quantities + ", name=" + name + "]";
	}

	

	
}
