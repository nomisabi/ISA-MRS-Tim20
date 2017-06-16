INSERT INTO usert (email, password, type) VALUES ('admin@admin.com','admin',0);
INSERT INTO usert (email, password, type) VALUES ('nena@gmail.com','nenasw',2);
INSERT INTO usert (email, password, type) VALUES ('nevena5695@gmail.com','123456',2);
INSERT INTO usert (email, password, type) VALUES ('mika@gmail.com','123456',2);
INSERT INTO usert (email, password, type) VALUES ('zika@gmail.com','123456',2);
INSERT INTO usert (email, password, type) VALUES ('nomisabi@gmail.com','nomisabi',2);
INSERT INTO usert (email, password, type) VALUES ('a@a.a','a',1);
INSERT INTO usert (email, password, type) VALUES ('b@b.b','b',4);

INSERT INTO guest (email, password, first_name,last_name,accepted) VALUES ('nena@gmail.com','nenasw','Nena','Djeric',true);
INSERT INTO guest (email, password, first_name,last_name,accepted) VALUES ('nomisabi@gmail.com','nomisabi','Noemi','Sabados',true);
INSERT INTO guest (email, password, first_name,last_name,accepted) VALUES ('pera@gmail.com','123456','Pera','Petrovic',true);
INSERT INTO guest (email, password, first_name,last_name,accepted) VALUES ('mika@gmail.com','123456','Mika','Mikic',true);
INSERT INTO guest (email, password, first_name,last_name,accepted) VALUES ('zika@gmail.com','123456','Zika','Petrovic',true);
INSERT INTO guest (email, password, first_name,last_name,accepted) VALUES ('luka@gmail.com','123456','Luka','Petrovic',true);
INSERT INTO friendship (id_friend, guest_id, request_accepted) VALUES (1,2, TRUE);
INSERT INTO friendship (id_friend, guest_id, request_accepted) VALUES (1,3, FALSE);
INSERT INTO friendship (id_friend, guest_id, request_accepted) VALUES (1,4, TRUE);
INSERT INTO friendship (id_friend, guest_id, request_accepted) VALUES (1,5, TRUE);

INSERT INTO supplier (email, password, name, active) VALUES ('b@b.b','b','b',true);

INSERT INTO sysman (email, password) VALUES ('admin@admin.com','admin') ;
INSERT INTO manager (email, password, first_name, last_name, active) VALUES ('a@a.a','a','a','a',true) ;

INSERT INTO food (name, description) VALUES ('Giros', 'Meso');
INSERT INTO food (name, description) VALUES ('Pljeskavica', 'Meso');
INSERT INTO food (name, description) VALUES ('Kapricoza', 'Pica');
INSERT INTO drink (name, description) VALUES ('Sprite', 'sok');
INSERT INTO drink (name, description) VALUES ('Fanta', 'sok');
INSERT INTO drink (name, description) VALUES ('Coca-Cola', 'sok');
INSERT INTO drink (name, description) VALUES ('Sok od jagode', 'sok');
INSERT INTO drink_menu_item (drink_id, price) VALUES (1, 150);
INSERT INTO drink_menu_item (drink_id, price) VALUES (2, 150);
INSERT INTO drink_menu_item (drink_id, price) VALUES (3, 150);
INSERT INTO drink_menu_item (drink_id, price) VALUES (4, 150);
INSERT INTO menu_item (food_id, price) VALUES (1, 300);
INSERT INTO menu_item (food_id, price) VALUES (2, 200);
INSERT INTO menu_item (food_id, price) VALUES (3, 700);

INSERT INTO menu (date_update) VALUES ('2017-05-13 20:00');
INSERT INTO drink_menu (date_update) VALUES ('2017-05-13 20:00');

INSERT INTO menu_items (menu_id, items_id) VALUES (1,1);
INSERT INTO menu_items (menu_id, items_id) VALUES (1,2);

INSERT INTO drink_menu_items (drink_menu_id, items_id) VALUES (1,1);
INSERT INTO drink_menu_items (drink_menu_id, items_id) VALUES (1,2);

INSERT INTO restaurant (name,location, lng, lat,species, menu_id,drink_menu_id) values ('Minuta','Novi Sad','19.83354959999997','45.2671352', 'Brza hrana',1,1);
INSERT INTO restaurant (name,location,lng, lat, species) values ('Ciao','Novi Sad', '19.83354959999997','45.2671352','Italijanska kuhinja');
INSERT INTO restaurant (name, location, lng, lat,species) values ('Dva Stapica','Novi Sad', '19.83354959999997','45.2671352','Kineski');
INSERT INTO restaurant (name, location,lng, lat,species) values ('Paprika','Novi Sad','19.83354959999997','45.2671352', 'Rositlj');
INSERT INTO restaurant (name, location,lng, lat,species) values ('Moja reka','Uzice','19.842093699999964','43.8554024', 'Srpska kuhinja');
INSERT INTO restaurant (name, location,lng, lat,species) values ('Burrito Madre','Beograd','20.44892159999995','44.786568', 'Meksikan');


INSERT INTO restaurant_manager (restaurant_id, manager_id) values (1,1);
INSERT INTO restaurant_suppliers (restaurant_id, suppliers_id) VALUES (1,1);

INSERT INTO region (name, restaurant_id) values ('basta',1);
INSERT INTO region (name, restaurant_id) values ('pusacki deo',1);
INSERT INTO region (name, restaurant_id) values ('nepusacki deo',1);
INSERT INTO region (name, restaurant_id) values ('terasa',1);

INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (1,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (2,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (3,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (6,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (7,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (8,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (9,2,1,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (4,4,2,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (5,4,3,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (10,2,3,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (11,2,3,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (12,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (13,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (14,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (15,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (16,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (17,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (18,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (19,2,2,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, region_id, restaurant_id) values (20,2,4,1);
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (1,2,2);
INSERT INTO table_of_restaurant (number, number_of_chairs, restaurant_id) values (2,4,2);

INSERT INTO reservation (restaurant_id, start_time, end_time, rate) VALUES (1,'24/05/2017 15:00','24/05/2017 16:00',false);
INSERT INTO table_reservation (reservation_id, table_id, start_time, end_time) VALUES (1,19,'24/05/2017 15:00','24/05/2017 16:00');
INSERT INTO guest_reservation (guest_id, reservation_id, accepted) VALUES (1, 1, TRUE);
INSERT INTO drink_menu_item_reservation (reservation_id, guest_id, drink_menu_item_id, prepared, quantity) VALUES (1,1,1,FALSE,1);
INSERT INTO menu_item_reservation (reservation_id, guest_id, menu_item_id, prepared, quantity) VALUES (1,1,1,FALSE,1);

INSERT INTO supply (chosed, from_date, name, to_date, rest_id, version) values (FALSE, '2017-05-29T23:30:00.000Z', 'Coca Cola (not stated)', '2017-05-30T23:30:00.000Z',1,0);
INSERT INTO supply (chosed, from_date, name, to_date, rest_id, version) values (FALSE, '2017-05-24T23:30:00.000Z', 'ananas (deadline exceeded)', '2017-05-24T23:30:00.000Z',1,0);
INSERT INTO supply (chosed, from_date, name, to_date, rest_id, version) values (FALSE, '2017-05-24T23:30:00.000Z', 'jabuka (no offer)', '2017-05-30T23:30:00.000Z',1,0);
INSERT INTO supply (chosed, from_date, name, to_date, rest_id, version) values (FALSE, '2017-05-24T23:30:00.000Z', 'jagoda (sended offer)', '2017-05-30T23:30:00.000Z',1,0);
INSERT INTO supply (chosed, from_date, name, to_date, rest_id, version) values (TRUE, '2017-05-24T23:30:00.000Z', 'Sprite (chosen offer)', '2017-05-30T23:30:00.000Z',1,0);

INSERT INTO offer (price, quality, status, supplier_id, supply_id) values (100,4,0,1,4);
INSERT INTO offer (price, quality, status, supplier_id, supply_id) values (100,5,2,1,5);

INSERT INTO rate_menu_item (rate, guest_id, menu_item_id, reservation_id) values (2,1,1,1);
INSERT INTO rate_menu_item (rate, guest_id, menu_item_id, reservation_id) values (3,1,1,1);
INSERT INTO rate_menu_item (rate, guest_id, menu_item_id, reservation_id) values (3,1,1,1);

INSERT INTO employee(first_name, last_name, email, password, active, numbC, numbS,type, date ) values ('f', 'ln', 'fn.ln@gmail.com','123', false, 32, 32, 1, '04/11/1995');
INSERT INTO employee(first_name, last_name, email, password, active, numbC, numbS,type, date) values ('emp2', 'emp2', 'emp.emp@gmail.com','emp', false, 32, 32, 1, '04/11/1995');

INSERT INTO restaurant_employee(restaurant_id, employee_id) values (1,1);
INSERT INTO restaurant_employee(restaurant_id, employee_id) values (1,2);

INSERT INTO rate_waiter(employee_id, restaurant_id, rate) values (1,1,1);
INSERT INTO rate_waiter(employee_id, restaurant_id, rate) values (1,1,3);
INSERT INTO rate_waiter(employee_id, restaurant_id, rate) values (2,1,4);

INSERT INTO bill(employee_id, restaurant_id, price, date) values (1,1,1010, '24/05/2017 15:00');
INSERT INTO bill(employee_id, restaurant_id, price, date) values (1,1, 710, '24/05/2017 16:00');
INSERT INTO bill(employee_id, restaurant_id, price, date) values (1,1,1010, '25/05/2017 15:00');
INSERT INTO bill(employee_id, restaurant_id, price, date) values (1,1,343.43, '22/04/2017 15:00');
INSERT INTO bill(employee_id, restaurant_id, price, date) values (2,1,283, '24/05/2017 15:00');
