/**
 * -----------------------------------------------------------------------------
 * Cook with ease | Big Cooking Data
 * -----------------------------------------------------------------------------
 *
 * Authors:
 *   Valentin BELYN
 *   Cyril BELMONTE
 *   Vincent ARCHAMBAULT
 *   Loïc TRAMIS
 *
 * Master 1 IISC 2018-2019
 * University of Cergy-Pontoise
 */
DROP DATABASE IF EXISTS CookWithEase;
CREATE DATABASE CookWithEase;
USE CookWithEase;


-- User table
CREATE TABLE User (
  id                INTEGER NOT NULL AUTO_INCREMENT,
  firstName         VARCHAR(50),
  lastName          VARCHAR(50),
  pseudo            VARCHAR(20),
  email             VARCHAR(50),
  password          VARCHAR(64),
  inscriptionDate   DATE,

  PRIMARY KEY (id)
);


-- Recipe tables
CREATE TABLE Recipe (
  id                INTEGER NOT NULL AUTO_INCREMENT,
  name              VARCHAR(100),
  duration          INTEGER,
  persons           INTEGER,
  type              ENUM('APPETIZER', 'STARTER', 'MAIN_COURSE', 'DESSERT', 'OTHER'),
  cost              ENUM('LOW', 'AVERAGE', 'HIGH'),
  difficulty        ENUM('LOW', 'AVERAGE', 'HIGH'),
  rating            FLOAT,
  picture           VARCHAR(200),

  PRIMARY KEY (id)
);

CREATE TABLE Step (
  id                INTEGER NOT NULL AUTO_INCREMENT,
  position          INTEGER,
  description       VARCHAR(500),
  recipeID          INTEGER,

  PRIMARY KEY (id),
  FOREIGN KEY (recipeID) REFERENCES Recipe (id)
);

CREATE TABLE Ingredient (
  id                INTEGER NOT NULL AUTO_INCREMENT,
  name              VARCHAR(50),
  quantity          FLOAT,
  unit              VARCHAR(20),
  recipeID          INTEGER,

  PRIMARY KEY (id),
  FOREIGN KEY (recipeID) REFERENCES Recipe (id)
);

CREATE TABLE Comment (
  userID            INTEGER NOT NULL,
  recipeID          INTEGER NOT NULL,

  description       VARCHAR(200),
  rating            INTEGER,
  publicationDate   DATE,

  PRIMARY KEY (userID, recipeID),
  FOREIGN KEY (userID) REFERENCES User (id),
  FOREIGN KEY (recipeID) REFERENCES Recipe (id)
);


-- Other tables
CREATE TABLE Bookmark (
  userID            INTEGER NOT NULL,
  recipeID          INTEGER NOT NULL,

  PRIMARY KEY (userID, recipeID),
  FOREIGN KEY (userID) REFERENCES User (id),
  FOREIGN KEY (recipeID) REFERENCES Recipe (id)
);

CREATE TABLE Friend (
  userID            INTEGER NOT NULL,
  friendID          INTEGER NOT NULL,

  PRIMARY KEY (userID, friendID),
  FOREIGN KEY (userID) REFERENCES User (id),
  FOREIGN KEY (friendID) REFERENCES User (id)
);
