package com.ucp.scrapper.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.Date;

public class Persistence {
	public static void main(String[] args) {
		Date creation = new Date(1553887404754L); // date in milliseconds
		User jeanJacques = new User("JeanJacques", "kekedu26540", 3, 6, creation);
		
		Ingredient tomate = new Ingredient(3, "Tomates", "1", "");
		Ingredient viande = new Ingredient(4, "Viandes", "100", "");
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		ingredients.add(tomate);
		ingredients.add(viande);

		Step step1 = new Step(1, "Mettre au four", "1");
		Step step2 = new Step(2, "Sortir du four", "1");
		List<Step> steps = new ArrayList<Step>();
		
		steps.add(step1);
		steps.add(step2);
		Category category = new Category("Vegan");
		Recipe tomateAuFour = new Recipe(2, 5, 2, 7, 2, "Tomate au four", "Facile", "Pas cher", "", ingredients, steps, category);
		
		insertUser(jeanJacques);
		insertIngredient(tomate);
		insertRecipe(tomateAuFour);
		
		Recipe resultAuFour = readRecipe(tomateAuFour);
		System.out.println(resultAuFour.toString());
		
		/* --- For IA mostly ---*/
//		Recipe newRecipe = new Recipe();
//		insertDistinctRecipe(newRecipe);
//		LinkedList<Ingredient> ingredient = readIngredients();
	}

	public static void insertUser(User user) {
		try {

			String insertAddressQuery = "INSERT INTO User (name, surname, like, share, creation_time) VALUES (?,?,?,?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setString	(1, user.getName());
			preparedStatement.setString	(2, user.getSurname());
			preparedStatement.setInt	(3, user.getLike());
			preparedStatement.setInt	(4, user.getShare());
			preparedStatement.setDate	(5, user.getCreationTime());

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static void insertCategory(Category category) {
		try {

			String insertAddressQuery = "INSERT INTO Category (description) VALUES (?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setString	(1, category.getDescription());

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static void insertRecipe(Recipe recipe) {
		try {

			String insertAddressQuery = "	INSERT INTO Recipe (mark, cookingTime, preparationTime, recipeTime, numberPersons, title, difficulty, economicLevel, picture, id_category)"
									+ "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setFloat	(1, recipe.getMark());
			preparedStatement.setInt	(2, recipe.getCookingTime());
			preparedStatement.setInt	(3, recipe.getPreparationTime());
			preparedStatement.setInt	(4, recipe.getRecipeTime());
			preparedStatement.setInt	(5, recipe.getNumberPersons());
			preparedStatement.setString	(6, recipe.getTitle());
			preparedStatement.setString	(7, recipe.getDifficulty());
			preparedStatement.setString	(8, recipe.getEconomicLevel());
			preparedStatement.setString	(9, recipe.getPicture());
			preparedStatement.setString	(10, recipe.getCategory().getId());

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static void insertIngredient(Ingredient ingredient) {
		try {

			String insertAddressQuery = "INSERT INTO Ingredient (quantity, name, url, id_recipe) VALUES (?,?,?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setInt	(1, ingredient.getQuantities());
			preparedStatement.setString	(2, ingredient.getName());
			preparedStatement.setString	(3, ""); // No picture for now
			preparedStatement.setString	(4, ingredient.getIdRecipe());

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	public static void insertComment(Comment comment) {
		try {

			String insertAddressQuery = "INSERT INTO Comment (text, date, user, mark, id_recipe) VALUES (?,?,?,?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setString	(1, comment.getText());
			preparedStatement.setDate	(2, comment.getDate());
			preparedStatement.setString	(3, comment.getUser().getId()); 
			preparedStatement.setString	(4, comment.getMark());
			preparedStatement.setString (5, comment.getIdRecipe());

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static void insertStep(Step step) {
		try {

			String insertAddressQuery = "INSERT INTO Step (step_number, instruction, id_recipe) VALUES (?,?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setInt	(1, step.getStepNumber());
			preparedStatement.setString	(2, step.getInstructions());
			preparedStatement.setString (3, step.getIdRecipe());
			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static Recipe readRecipe(Recipe recipe) {
		Recipe readRecipe = new Recipe();
		try {

			String selectAddressQuery = "	SELECT * "
					+ "						FROM Recipe AS r "
					+ "						WHERE r.id = ? AND r.title = ?";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAddressQuery);

			preparedStatement.setString(1, recipe.getId());
			preparedStatement.setString(2, recipe.getTitle());

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				readRecipe.setId(result.getString("id"));
				readRecipe.setTitle(result.getString("title"));
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readRecipe;
	}
	
	public static void insertDistinctRecipe(Recipe recipe) {
		Recipe readRecipe = new Recipe();
		
		try {
			String selectAddressQuery = "	SELECT * "
					+ "						FROM Recipe AS r "
					+ "						WHERE r.id = ? AND r.title = ?";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedSelectStatement = dbConnection.prepareStatement(selectAddressQuery);

			preparedSelectStatement.setString(1, recipe.getId());
			preparedSelectStatement.setString(2, recipe.getTitle());


			ResultSet result = preparedSelectStatement.executeQuery();
			preparedSelectStatement.close(); // maybe delete this line of code IDK
			
			if (result.getRow() == 0) {
				String insertAddressQuery = "	INSERT INTO Recipe (mark, cookingTime, preparationTime, recipeTime, numberPersons, title, difficulty, economicLevel, picture, id_category)"
						+ "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

				PreparedStatement preparedInsertStatement = dbConnection.prepareStatement(insertAddressQuery);
				
				//Set values of parameters in the query.
				preparedInsertStatement.setFloat	(1, recipe.getMark());
				preparedInsertStatement.setInt		(2, recipe.getCookingTime());
				preparedInsertStatement.setInt		(3, recipe.getPreparationTime());
				preparedInsertStatement.setInt		(4, recipe.getRecipeTime());
				preparedInsertStatement.setInt		(5, recipe.getNumberPersons());
				preparedInsertStatement.setString	(6, recipe.getTitle());
				preparedInsertStatement.setString	(7, recipe.getDifficulty());
				preparedInsertStatement.setString	(8, recipe.getEconomicLevel());
				preparedInsertStatement.setString	(9, recipe.getPicture());
				preparedInsertStatement.setString	(10, recipe.getCategory().getId());
				
				preparedInsertStatement.executeUpdate();
				
				preparedInsertStatement.close();
			}
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	/*
	public static LinkedList<Ingredient> readIngredients() {
		String countQuery = "SELECT count(*) FROM Ingredient";
		 
		LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();
		
		Connection dbConnection = JdbcConnection.getConnection();
		PreparedStatement preparedStatement = dbConnection.prepareStatement(countQuery);

		ResultSet result = preparedStatement.executeQuery();
		
		for (int index = 0; index < result.getRow(); index++) {
			ingredients.add(getIngredient(index));
		}
		
		return ingredients;
	}*/
	/*
	private static Ingredient getIngredient(int index){
		 
		String selectQuery = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	 
		Ingredient ingredient = (Ingredient)getJdbcTemplate().queryForObject(
				selectQuery, new Object[] { index }, new IngredientRowMapper());
			
		return ingredient;
	}
	*/

}
