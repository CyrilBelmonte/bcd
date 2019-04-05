DROP DATABASE IF EXISTS BigCookingData;

CREATE DATABASE BigCookingData;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Shares;
DROP TABLE IF EXISTS Likes;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Step;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Recipe;

CREATE TABLE Comments (
    id_comment INT NOT NULL AUTO_INCREMENT,
    text VARCHAR(500),
    date DATE,
    user VARCHAR(50),
    mark VARCHAR(20),
    id_recipe INT,
    PRIMARY KEY(id_comment)
);
CREATE TABLE Ingredient (
    id_ingredient INT NOT NULL AUTO_INCREMENT,
    quantity INT,
    name VARCHAR(50),
    picture BLOB,
    id_recipe INT,
    PRIMARY KEY(id_ingredient)
);
CREATE TABLE Step (
    id_step INT NOT NULL AUTO_INCREMENT,
    step_Number INT,
    instruction VARCHAR(500),
    PRIMARY KEY(id_step)
);

CREATE TABLE Recipe (
    id_recipe INT NOT NULL AUTO_INCREMENT,
    mark VARCHAR(50),
    id_ingredient INT,
    id_comment INT,
    id_step INT,
    cookingTime INT,
    preparationTime INT,
    recipeTime INT,
    PRIMARY KEY (id_recipe),
    FOREIGN KEY (id_ingredient) REFERENCES Ingredient(id_ingredient),
    FOREIGN KEY (id_comment) REFERENCES Comments(id_comment),
    FOREIGN KEY (id_step) REFERENCES Step(id_step)
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

CREATE TABLE Category (
    id_category INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(200),
    id_recipe INT,
    PRIMARY KEY(id_category),
    FOREIGN KEY (id_recipe) REFERENCES Recipe(id_recipe)
);