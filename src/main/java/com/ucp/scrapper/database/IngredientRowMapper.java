<<<<<<<Updated upstream
/*
package src.main.java.com.ucp.scrapper.database;
=======
package com.ucp.scrapper.database;
>>>>>>> Stashed changes

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IngredientRowMapper implements RowMapper {

	public Object mapRow(ResultSet result, int rowNum) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(result.getString("id_ingredient"));
		ingredient.setQuantities(result.getInt("quantity"));
		ingredient.setName(result.getString("name"));
		ingredient.setUrl(result.getString("url"));
		ingredient.setIdRecipe(result.getString("id_recipe"));
		
		return ingredient;
	}
		
}
*/