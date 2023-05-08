select 1 from dual;

INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Mooon', 'US'); 
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO customer(name) VALUES ('Lola Mento');
INSERT INTO customer(name) VALUES ('Silvia Ducto');
INSERT INTO customer(name) VALUES ('Nico Menideja');
INSERT INTO customer(name) VALUES ('Pep Apig');
INSERT INTO customer(name) VALUES ('Epifanio Blasón');
INSERT INTO customer(name) VALUES ('Tipo de incógnito');

INSERT INTO loan(game_id, customer_id, loan_date, return_date) VALUES 
(1, 1, '2023-04-23', '2023-04-28');
INSERT INTO loan(game_id, customer_id, loan_date, return_date) VALUES 
(2, 1, '2023-04-25', '2023-04-30');
INSERT INTO loan(game_id, customer_id, loan_date, return_date) VALUES 
(3, 2, '2023-06-23', '2023-06-28');
INSERT INTO loan(game_id, customer_id, loan_date, return_date) VALUES 
(4, 3, '2023-07-23', '2023-07-28');
INSERT INTO loan(game_id, customer_id, loan_date, return_date) VALUES 
(5, 4, '2023-08-23', '2023-08-28');
INSERT INTO loan(game_id, customer_id, loan_date, return_date) VALUES 
(6, 5, '2023-09-23', '2023-09-28');

