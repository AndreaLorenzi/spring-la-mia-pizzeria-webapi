INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('pizza rossa con pomodoro e mozzarella', 'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/5802fab5-fdce-468a-a830-43e8001f5a72/Derivates/c00dc34a-e73d-42f0-a86e-e2fd967d33fe.jpg', 'Margherita', 8.99);
INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('Peperoni, cipolla, olive nere', 'https://sipbitego.com/wp-content/uploads/2021/08/Pepperoni-Pizza-Recipe-Sip-Bite-Go.jpg', 'Pizza Pepperoni', 10.99);
INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('Salsiccia piccante, peperoncino, formaggio', 'https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRuO2rLe1U4Q5oN_z1AKZbZEpZ0OsTFhB0Ys_t5qvM5ZxZi03UUv6TxbR3g-5AVDd8_UPH-9ZDF9mzZDugjp_IIgysAXlLj01ojePXZX_ztnXTPOa-w-J9ZaVg&usqp=CAE', 'Pizza Diavola', 11.99);
INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('Melanzane, zucchine, peperoni', 'https://blog.giallozafferano.it/lericettediamanda/wp-content/uploads/2020/10/IMG_20201003_191155-1-320x303.jpg', 'Pizza Vegetariana', 9.99);
INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('Salsiccia, funghi, mozzarella', 'https://www.alfaforni.com/wp-content/uploads/2018/08/pizza-boscaiola-wild-traditional-mushroom-pizza.jpg', 'Pizza boscaiola', 11.99);
INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('Prosciutto crudo, funghi, rucola', 'https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcS_7fjwzt6k3l8JMA7UawzdFClpxEcWQFrmKIg0mcjVjRuWQ8kqiSixdhDS8UgXJPgnsx4aeDUoh-vjYn7po_PrRmq_y83EJ3Auo_O49iaP90WqV9FlgApVtQ&usqp=CAE', 'Pizza Prosciutto e Funghi', 12.99);
INSERT INTO db_pizza.pizzas(description, image_url, name, price)VALUES('Gorgonzola, noci, pere', 'https://www.igorgorgonzola.com/sistemaNews/newsFoto/videoricette2014/pizza-gourmet-pere-gorgonzola-gran-riserva-leonardi-L.jpg', 'Pizza Gorgonzola e Pere', 11.99);


INSERT INTO ingredients(name) VALUES('Peperoni');
INSERT INTO ingredients(name) VALUES('Funghi');
INSERT INTO ingredients(name) VALUES('Mozzarella');
INSERT INTO ingredients(name) VALUES('Pomodoro');
INSERT INTO ingredients(name) VALUES('Cipolla');
INSERT INTO ingredients(name) VALUES('Peperoncino');
INSERT INTO ingredients(name) VALUES('Zucchine');
INSERT INTO ingredients(name) VALUES('noci');
INSERT INTO ingredients(name) VALUES('Prosciutto crudo');
INSERT INTO ingredients(name) VALUES('Rucola');
INSERT INTO ingredients(name) VALUES('Formaggio');
INSERT INTO ingredients(name) VALUES('Olive nere');
INSERT INTO ingredients(name) VALUES('Pere');
INSERT INTO ingredients(name) VALUES('Salsiccia');
INSERT INTO ingredients(name) VALUES('Salsiccia piccante');
INSERT INTO ingredients(name) VALUES('Gorgonzola');

INSERT INTO pizzas_ingredients(pizza_id, ingredients_id) VALUES(1,1);
INSERT INTO pizzas_ingredients(pizza_id, ingredients_id) VALUES(1,3);

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('john@email.com', 'John', 'Doe', '2023-11-20 10:35', '{noop}john');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('jane@email.com', 'Jane', 'Smith', '2023-11-20 10:35','{noop}jane');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);