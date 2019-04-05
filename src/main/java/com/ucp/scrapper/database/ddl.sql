DROP DATABASE IF EXISTS BigCookingData;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Shares;
DROP TABLE IF EXISTS Likes;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Steps;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Recipes;

CREATE DATABASE BigCookingData;

CREATE TABLE User (
    id_user INT NOT NULL AUTO_INCREMENT,
    name VARCHAR2(50),
    surname VARCHAR2(50),
    likes INT,
    shares INT,
    creation_time DATE,
    PRIMARY KEY(id_user)
);
CREATE TABLE Share (
    id_share INT NOT NULL AUTO_INCREMENT,
    id_recipe INT,
    id_user INT,
    PRIMARY KEY(id_share),    
    FOREIGN KEY (id_user) REFERENCES User(id_user),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipes)
);
CREATE TABLE Like (
    id_like INT NOT NULL AUTO_INCREMENT,
    id_user INT,
    id_recipe INT,
    PRIMARY KEY(id_like),
    FOREIGN KEY (id_user) REFERENCES User(id_user),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);
CREATE TABLE Comment (
    id_comment INT NOT NULL AUTO_INCREMENT,
    text VARCHAR2(500),
    date DATE,
    user VARCHAR2(50),
    mark VARCHAR2(20),
    id_recipe INT,
    PRIMARY KEY(id_comment),
);
CREATE TABLE Ingredient (
    id_ingredient INT NOT NULL AUTO_INCREMENT,
    quantity INT,
    name VARCHAR2(50),
    picture BLOB,
    id_recipe INT
    PRIMARY KEY(id_ingredient),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);
CREATE TABLE Step (
    id_step INT NOT NULL AUTO_INCREMENT,
    step_Number INT,
    instruction VARCHAR2(500),
    PRIMARY KEY(id_step)
);
CREATE TABLE Category (
    id_category INT NOT NULL AUTO_INCREMENT,
    description VARCHAR2(200),
    id_recipe INT,
    PRIMARY KEY(id_category),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)    
);
CREATE TABLE Recipe (
    id_recipe INT NOT NULL AUTO_INCREMENT,
    mark VARCHAR2(50),
    id_ingredient INT,
    id_comment INT,
    id_step INT,
    cookingTime INT,
    preparationTime INT,
    recipeTime INT,
    PRIMARY KEY (id_recipe),
    FOREIGN KEY (id_ingredient) REFERENCES Ingredient(id_ingredient),
    FOREIGN KEY (id_comment) REFERENCES Comment(id_comment),
    FOREIGN KEY (id_step) REFERENCES Step(id_step)
);