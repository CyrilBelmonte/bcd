package com.ucp.cookwithease.dao.lucene;

import com.ucp.cookwithease.dao.general.Index;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.nio.file.Path;
import java.util.LinkedList;


public class LuceneIndex implements Index {
    private Path indexPath;
    private LuceneIndexer luceneIndexer;
    private LuceneSearcher luceneSearcher;

    public LuceneIndex(Path indexPath) {
        this.indexPath = indexPath;

        luceneIndexer = new LuceneIndexer(indexPath);
        luceneSearcher = new LuceneSearcher(indexPath);
    }

    @Override
    public boolean open() {
        return luceneIndexer.openIndex();
    }

    @Override
    public boolean create() {
        return luceneIndexer.createIndex();
    }

    @Override
    public boolean close() {
        return luceneIndexer.close();
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        Document document = getDocumentFromRecipe(recipe);

        return luceneIndexer.indexDocument(document);
    }

    @Override
    public boolean addRecipes(LinkedList<Recipe> recipes) {
        boolean hasSucceeded = true;

        for (Recipe recipe : recipes) {
            hasSucceeded = hasSucceeded && addRecipe(recipe);
        }

        return hasSucceeded;
    }

    @Override
    public LinkedList<Integer> findRecipesId(String keywords, int maxResults) {
        String[] fields = {"name", "type", "ingredients"};

        int recipeID;
        LinkedList<Integer> recipesID = new LinkedList<>();
        LinkedList<Document> documents = luceneSearcher.search(keywords, maxResults, fields);

        for (Document document : documents) {
            recipeID = Integer.parseInt(document.get("id"));
            recipesID.addLast(recipeID);
        }

        return recipesID;
    }

    private Document getDocumentFromRecipe(Recipe recipe) {
        LinkedList<Ingredient> ingredients = recipe.getIngredients();

        String recipeID = Integer.toString(recipe.getId());
        String recipeName = recipe.getName();
        String recipeType;

        switch (recipe.getType()) {
            case APPETIZER:
                recipeType = "apéritif";
                break;

            case STARTER:
                recipeType = "entrée";
                break;

            case MAIN_COURSE:
                recipeType = "plat";
                break;

            case DESSERT:
                recipeType = "dessert";
                break;

            default:
                recipeType = "";
        }

        Document document = new Document();

        document.add(new StringField("id", recipeID, Field.Store.YES));
        document.add(new TextField("name", recipeName, Field.Store.NO));
        document.add(new TextField("type", recipeType, Field.Store.NO));

        for (Ingredient ingredient : ingredients) {
            document.add(new TextField("ingredients", ingredient.getName(), Field.Store.NO));
        }

        return document;
    }
}
