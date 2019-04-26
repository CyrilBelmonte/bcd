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
USE CookWithEase;

-- User table
INSERT INTO User (id, firstName, lastName, pseudo, email, password, inscriptionDate) VALUES
  (1, 'Valentin', 'Belyn', 'ValentinB', 'valentin@cook-with-ease.com', SHA2('passwordVB', 256), '2019-01-07'),
  (2, 'Cyril', 'Belmonte', 'gb4794', 'cyril@cook-with-ease.com', SHA2('passwordCB', 256), '2019-02-23'),
  (3, 'Vincent', 'Archambault', 'Zielfing', 'vincent@cook-with-ease.com', SHA2('passwordVA', 256), '2019-03-12'),
  (4, 'Loïc', 'Tramis', 'Entropy', 'loic@cook-with-ease.com', SHA2('passwordLT', 256), '2019-04-02');

-- Recipe table
INSERT INTO Recipe (id, name, duration, persons, type, cost, difficulty, rating, picture) VALUES
  (1, 'Velouté de champignons', 50, 3, 'STARTER', 'LOW', 'LOW', 4.5, 'https://assets.afcdn.com/recipe/20160404/2282_w420h344c1cx1500cy1000.jpg'),
  (2, 'Tartine de chèvre au miel', 20, 8, 'STARTER', 'LOW', 'LOW', 5, 'https://assets.afcdn.com/recipe/20150807/15555_w420h344c1cx2808cy1872.jpg');

-- Step table
INSERT INTO Step (position, description, recipeID) VALUES
  (1, 'Faire fondre dans une casserole 3 cuillères à soupe de beurre.', 1),
  (2, 'Ajouter un petit oignon haché et les champignons de Paris coupés en tout petits morceaux.', 1),
  (3, 'Saler, poivrer, saupoudrer d’une cuillère à soupe de persil haché.', 1),
  (4, 'Remuer bien les champignons dans le beurre chaud. Couvrir et laisser cuire à feu doux 1/4 heure.', 1),
  (5, 'Ajouter alors 2 cuillères à soupe de farine en remuant sans cesse. Ajouter 1/4 litre de bouillon (ou d’eau) et 1/2 litre de lait.', 1),
  (6, 'Faire cuire à feu doux en remuant de temps en temps.', 1),
  (7, 'Lorsque le potage commence à bouillir, baisser le feu et laisser cuire à découvert encore 1/4 d’heure.', 1),
  (8, 'Quelques minutes avant de servir ajouter le jus d’un citron puis 2 cuillères à soupe de crème fraîche.', 1),

  (1, 'Préchauffer le four Th 6 (four au gaz).', 2),
  (2, 'Couper les tranches de pain en 3.', 2),
  (3, 'Enduire les morceaux de miel.', 2),
  (4, 'Couper les chèvres en rondelles et les mettre sur les tartines badigonnées de miel.', 2),
  (5, 'Saupoudrez de cumin.', 2),
  (6, 'Mettre les morceaux de pain sur un plat allant au four (tourtière ou autre).', 2),
  (7, 'Mettre au four.', 2),
  (8, 'Sortir lorsque le chèvre fait de petites bulles.', 2);

-- Ingredient table
INSERT INTO Ingredient (name, quantity, unit, recipeID) VALUES
  ('beurre', 3, 'cuillères à soupe', 1),
  ('oignon', 1, NULL, 1),
  ('champignon de Paris', 250, 'g', 1),
  ('farine', 2, 'cuillères à soupe', 1),
  ('bouillon (ou d\'eau)', 0.25, 'l', 1),
  ('lait', 0.5, 'l', 1),
  ('citron', 1, NULL, 1),
  ('crème fraîche', 2, 'cuillères à soupe', 1),
  ('persil haché', 1, 'cuillère à soupe', 1),
  ('Sel', 0, NULL, 1),
  ('Poivre', 0, NULL, 1),

  ('pain', 8, 'tranches', 2),
  ('chèvre', 2, 'buches', 2),
  ('Miel', 0, NULL, 2),
  ('Cumin', 0, NULL, 2);

-- Comment table
INSERT INTO Comment (userID, recipeID, description, rating, publicationDate) VALUES
  (1, 1, 'Excellente recette. Je suis très content du résultat.', 5, '2019-01-09'),
  (3, 1, 'Délicieux, j’ai mixé 1/3 du velouté et je l’ai mélangé avec le reste, ça donne plus de goût au potage.', 4, '2019-03-01'),
  (2, 2, 'Excellent. J\'ai pris de la baguette pour en faire des petits toasts et j\'ai fait moitié cumin, moitié thym.', 5, '2019-04-12');

-- Other tables
INSERT INTO Bookmark (userID, recipeID) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (3, 1);

INSERT INTO Friend (userID, friendID) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 1),
  (2, 4);
