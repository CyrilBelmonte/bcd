DROP DATABASE IF EXISTS BigCookingData;

CREATE DATABASE BigCookingData;

DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Step;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Shares;
DROP TABLE IF EXISTS Likes;

CREATE TABLE Category (
    id_category INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(200),
    PRIMARY KEY(id_category)
);
CREATE TABLE Recipe (
    id_recipe INT NOT NULL AUTO_INCREMENT,
    mark FLOAT,
    cookingTime INT,
    preparationTime INT,
    recipeTime INT,
    numberPersons INT,
    title VARCHAR(50),
    difficulty VARCHAR(50),
    economicLevel VARCHAR(50),
    picture VARCHAR(50),
    id_category INT,
    PRIMARY KEY (id_recipe),
    FOREIGN KEY (id_category) REFERENCES Category(id_category)
);
CREATE TABLE Ingredient (
    id_ingredient INT NOT NULL AUTO_INCREMENT,
    quantity INT,
    name VARCHAR(50),
    url VARCHAR(50),
    id_recipe INT,
    PRIMARY KEY(id_ingredient),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);
CREATE TABLE Step (
    id_step INT NOT NULL AUTO_INCREMENT,
    step_Number INT,
    instruction VARCHAR(500),
    id_recipe INT,
    PRIMARY KEY(id_step),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);
CREATE TABLE Comments (
    id_comment INT NOT NULL AUTO_INCREMENT,
    text VARCHAR(500),
    date DATE,
    user VARCHAR(50),
    mark VARCHAR(20),
    id_recipe INT,
    PRIMARY KEY(id_comment),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);
CREATE TABLE Users (
    id_user INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    likes INT,
    shares INT,
    creation_time DATE,
    PRIMARY KEY(id_user)
);
CREATE TABLE Shares (
    id_share INT NOT NULL AUTO_INCREMENT,
    id_recipe INT,
    id_user INT,
    PRIMARY KEY(id_share),
    FOREIGN KEY (id_user) REFERENCES Users(id_user),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);
CREATE TABLE Likes (
    id_like INT NOT NULL AUTO_INCREMENT,
    id_user INT,
    id_recipe INT,
    PRIMARY KEY(id_like),
    FOREIGN KEY (id_user) REFERENCES Users(id_user),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);