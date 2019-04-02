package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;

public class Persistence {
	public static void main(String[] args) {
		Date creation = new Date(1553887404754L); // date in milliseconds
		User jeanJacques = new User("JeanJacques", "kekedu26540", 3, 6, creation);
		
		Ingredient tomate = new Ingredient(3, "Tomates", "1");
		
		Comment comment = new Comment("Trop bien", creation, jeanJacques, "mark", "1");
		Step etape = new Step(1, "Mettre au four");
		Recipe tomateAuFour = new Recipe("Ultra dur", tomate, comment, etape, 5, 20, 25);
		Category dinner = new Category("Vegan", tomateAuFour);
		
		insertUser(jeanJacques);
		insertIngredient(tomate);
		insertComment(comment);
		insertStep(etape);
		insertRecipe(tomateAuFour);
		insertCategory(dinner);
		
		Recipe resultAuFour = readRecipe(tomateAuFour);
		System.out.println(resultAuFour.toString());

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
	
	public static void insertIngredient(Ingredient ingredient) {
		try {

			String insertAddressQuery = "INSERT INTO Ingredient (quantity, name, picture, id_recipe) VALUES (?,?,?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setInt	(1, ingredient.getQuantities());
			preparedStatement.setString	(2, ingredient.getName());
			preparedStatement.setInt	(3, 0); // No picture for now
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

			String insertAddressQuery = "INSERT INTO Step (step_number, instruction) VALUES (?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setInt	(1, step.getStepNumber());
			preparedStatement.setString	(2, step.getInstructions());
			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static void insertRecipe(Recipe recipe) {
		try {

			String insertAddressQuery = "INSERT INTO Recipe (mark, id_ingredient, id_comment, id_step, cookingTime, preparationTime, recipeTime) VALUES (?,?,?,?,?,?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setString	(1, recipe.getMark());
			preparedStatement.setString	(2, recipe.getIngredient().getId());
			preparedStatement.setString	(3, recipe.getComment().getId());
			preparedStatement.setString	(4, recipe.getStep().getId());
			preparedStatement.setInt	(5, recipe.getCookingTime());
			preparedStatement.setInt	(6, recipe.getPreparationTime());
			preparedStatement.setInt	(7, recipe.getRecipeTime());

			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static void insertCategory(Category category) {
		try {

			String insertAddressQuery = "INSERT INTO Category (description, id_recipe) VALUES (?,?)";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertAddressQuery);

			//Set values of parameters in the query.
			preparedStatement.setString	(1, category.getDescription());
			preparedStatement.setString	(2, category.getRecipe().getId());

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
					+ "						WHERE r.id = ? AND r.mark = ?";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAddressQuery);

			preparedStatement.setString(1, recipe.getId());
			preparedStatement.setString(2, recipe.getMark());

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				readRecipe.setId(result.getString("id"));
				readRecipe.setMark(result.getString("mark"));
				readRecipe.setIngredient((Ingredient) result.getObject("ingredient"));
				readRecipe.setComment((Comment) result.getObject("comment"));
				readRecipe.setStep((Step) result.getObject("step"));
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readRecipe;
	}
}
