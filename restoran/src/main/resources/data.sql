INSERT INTO usert (email, password, type) VALUES ('admin@admin.com','admin',0);
INSERT INTO usert (email, password, type) VALUES ('nena@gmail.com','nenasw',2);
INSERT INTO usert (email, password, type) VALUES ('pera@gmail.com','123456',2);
INSERT INTO usert (email, password, type) VALUES ('mika@gmail.com','123456',2);
INSERT INTO guest (email, password, first_name,last_name) VALUES ('nena@gmail.com','nenasw','Nena','Djeric');
INSERT INTO guest (email, password, first_name,last_name) VALUES ('pera@gmail.com','123456','Pera','Petrovic');
INSERT INTO guest (email, password, first_name,last_name) VALUES ('mika@gmail.com','123456','Mika','Mikic');
INSERT INTO sysman (email, password) VALUES ('admin@admin.com','admin') ;
INSERT INTO restaurant (name, location) values ('Nasa Prica','UE');
INSERT INTO restaurant (name, location) values ('Dva Stapica','Bla');
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (1,2,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (2,2,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (3,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (1,2,2);
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (2,4,2);

