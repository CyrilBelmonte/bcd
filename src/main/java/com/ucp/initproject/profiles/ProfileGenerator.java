package com.ucp.initproject.profiles;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;
import com.ucp.xml.exist.query.QueryCategory;
import com.ucp.xml.exist.query.QuerySimpleUser;
import com.ucp.xml.exist.query.QueryUser;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class ProfileGenerator {
    private QueryCategory queryCategory;
    private QuerySimpleUser querySimpleUser;
    private QueryUser queryUser;

    private LinkedList<String> commentsFiveStars;
    private LinkedList<String> commentsFourStars;

    private String firstName;
    private String lastName;
    private String pseudo;
    private LinkedList<String> favoriteStarters;
    private LinkedList<String> favoriteMainCourses;
    private LinkedList<String> favoriteDesserts;

    public ProfileGenerator() {
        queryCategory = new QueryCategory();
        querySimpleUser = new QuerySimpleUser();
        queryUser = new QueryUser();

        favoriteStarters = new LinkedList<>();
        favoriteMainCourses = new LinkedList<>();
        favoriteDesserts = new LinkedList<>();

        loadComments();
    }

    public void loadComments() {
        String dictionary = "./src/main/java/com/ucp/initproject/profiles/dictionaries/comments.yaml";

        Yaml yaml = new Yaml();
        HashMap<Integer, List<String>> commentsDict;

        try {
            InputStream inputStream = new FileInputStream(new File(dictionary));
            commentsDict = yaml.load(inputStream);

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] loadComments : " + e.getMessage());
            return;
        }

        commentsFiveStars = new LinkedList<>();
        commentsFiveStars.addAll(commentsDict.get(5));

        commentsFourStars = new LinkedList<>();
        commentsFourStars.addAll(commentsDict.get(4));
    }

    public boolean generate() {
        System.out.println("[PROFILE] Creating the profile " + firstName + " " + lastName + " (" + pseudo + ")");

        User user = getUser();
        boolean hasSucceeded = insertUser(user);

        if (!hasSucceeded) {
            System.out.println("[PROFILE] " + firstName + " " + lastName + ": Failed");
            return false;
        }

        System.out.println("[PROFILE] " + firstName + " " + lastName + ": adding comments on starters...");
        generateCommentsOnDishType(user, favoriteStarters, "entrée");

        System.out.println("[PROFILE] " + firstName + " " + lastName + ": adding comments on main courses...");
        generateCommentsOnDishType(user, favoriteMainCourses, "plat");

        System.out.println("[PROFILE] " + firstName + " " + lastName + ": adding comments on desserts...");
        generateCommentsOnDishType(user, favoriteDesserts, "dessert");

        System.out.println("[PROFILE] " + firstName + " " + lastName + ": OK");

        return true;
    }

    private void generateCommentsOnDishType(User user, LinkedList<String> recipesName, String type) {
        boolean hasSucceeded;
        LinkedList<Integer> recipesID;

        for (String recipeName : recipesName) {
            recipeName += " " + type;

            recipesID = DAOFactory.getIndex().findRecipesId(recipeName, 10);

            for (int recipeID : recipesID) {
                hasSucceeded = generateCommentsOnSimilarRecipes(
                    user, recipeID, 2, 3);

                if (hasSucceeded) {
                    break;
                }
            }
        }
    }

    private boolean generateCommentsOnSimilarRecipes(
            User user, int recipeID, int minComments, int maxComments) {

        LinkedList<Integer> recipesID = new LinkedList<>(
            queryCategory.findRecipe(recipeID));

        Collections.shuffle(recipesID);

        Comment comment;
        int currentRecipeID;
        int maxRecipes = recipesID.size();

        if (maxRecipes < minComments) {
            return false;
        }

        for (int i = 0; i < maxRecipes && i < maxComments; i++) {
            currentRecipeID = recipesID.get(i);

            boolean isAlreadyCommented = DAOFactory.getCommentDAO().find(
                user.getId(), currentRecipeID) != null;

            if (isAlreadyCommented) {
                continue;
            }

            comment = getRandomComment(user, currentRecipeID);
            insertComment(comment);
        }

        return true;
    }

    private User getUser() {
        Date inscriptionDate = getDateFromLocalDate(getRandomDate(
            LocalDate.of(2019, 1, 1),
            LocalDate.of(2019, 3, 31)));

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPseudo(pseudo);
        user.setEmail(pseudo + "@cook-with-ease.fr");
        user.setPassword(Tools.sha256("_password_"));
        user.setInscriptionDate(inscriptionDate);

        return user;
    }

    private Comment getRandomComment(User user, int recipeID) {
        Date publicationDate = getDateFromLocalDate(getRandomDate(
            getLocalDateFromDate(user.getInscriptionDate()),
            LocalDate.of(2019, 6, 16)));

        String description;
        int rating = getRandomRating();

        if (rating > 4) {
            description = getRandomString(commentsFiveStars);

        } else {
            description = getRandomString(commentsFourStars);
        }

        Comment comment = new Comment();

        comment.setUserID(user.getId());
        comment.setRecipeID(recipeID);
        comment.setDescription(description);
        comment.setRating(rating);
        comment.setPublicationDate(publicationDate);

        return comment;
    }

    private boolean insertUser(User user) {
        boolean hasSucceeded = DAOFactory.getUserDAO().insert(user);

        if (!hasSucceeded) {
            return false;

        } else {
            queryUser.addSimpleUser(user.getId());

            return true;
        }
    }

    private boolean insertComment(Comment comment) {
        boolean hasSucceeded = DAOFactory.getCommentDAO().insert(comment);

        if (!hasSucceeded) {
            return false;

        } else {
            querySimpleUser.majCat(
                comment.getUserID(),
                comment.getRecipeID(),
                comment.getRating());

            return true;
        }
    }

    private String getRandomString(LinkedList<String> strings) {
        int pickedIndex = (int) (Math.random() * (strings.size() - 1));
        return strings.get(pickedIndex);
    }

    private int getRandomRating() {
        return (Math.random() >= 0.25) ? 5 : 4;
    }

    private LocalDate getRandomDate(LocalDate minDate, LocalDate maxDate) {
        Random random = new Random();

        int minDay = (int) LocalDate.of(
            minDate.getYear(),
            minDate.getMonth(),
            minDate.getDayOfMonth()
        ).toEpochDay();

        int maxDay = (int) LocalDate.of(
            maxDate.getYear(),
            maxDate.getMonth(),
            maxDate.getDayOfMonth()
        ).toEpochDay();

        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return randomDate;
    }

    private Date getDateFromLocalDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    private LocalDate getLocalDateFromDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public void newProfile() {
        firstName = null;
        lastName = null;
        pseudo = null;

        favoriteStarters.clear();
        favoriteMainCourses.clear();
        favoriteDesserts.clear();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setFavoriteStarters(LinkedList<String> favoriteStarters) {
        this.favoriteStarters = favoriteStarters;
    }

    public void setFavoriteMainCourses(LinkedList<String> favoriteMainCourses) {
        this.favoriteMainCourses = favoriteMainCourses;
    }

    public void setFavoriteDesserts(LinkedList<String> favoriteDesserts) {
        this.favoriteDesserts = favoriteDesserts;
    }

    public void addFavoriteStarters(String favoriteStarter) {
        favoriteStarters.addLast(favoriteStarter);
    }

    public void addFavoriteMainCourses(String favoriteMainCourse) {
        favoriteMainCourses.addLast(favoriteMainCourse);
    }

    public void addFavoriteDesserts(String favoriteDessert) {
        favoriteDesserts.addLast(favoriteDessert);
    }
}
