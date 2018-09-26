# Burgerly Server
A Spring Boot server application created to customize and buy burgers.

## Clone
Run `git clone https://github.com/robertoamadeuneto/burgerly-server.git`.

## Build
To build this project, run `mvn -Pdevelopment clean install`. This profile will not run the automated tests.

## Run
A PostgreSQL database called `burgerly` is required to run this project. 
Once you create it, you need to execute the commands below to create the application's default records:

```
-- Ingredients
insert into ingredient (description, price) values ('Alface', 0.4);
insert into ingredient (description, price) values ('Bacon', 2.0);
insert into ingredient (description, price) values ('Hambúrger de carne', 3.0);
insert into ingredient (description, price) values ('Ovo', 0.8);
insert into ingredient (description, price) values ('Queijo', 1.5);

-- Burgers
insert into burger (description) values ('X-Bacon');
insert into burger (description) values ('X-Burger');
insert into burger (description) values ('X-Egg');
insert into burger (description) values ('X-Egg Bacon');

-- X-Bacon Ingredients
insert into burger_ingredient (burger_id, ingredient_id) values (1, 2);
insert into burger_ingredient (burger_id, ingredient_id) values (1, 3);
insert into burger_ingredient (burger_id, ingredient_id) values (1, 5);

-- X-Burger Ingredients
insert into burger_ingredient (burger_id, ingredient_id) values (2, 3);
insert into burger_ingredient (burger_id, ingredient_id) values (2, 5);

-- X-Egg Ingredients
insert into burger_ingredient (burger_id, ingredient_id) values (3, 3);
insert into burger_ingredient (burger_id, ingredient_id) values (3, 4);
insert into burger_ingredient (burger_id, ingredient_id) values (3, 5);

-- X-Egg Bacon Ingredients
insert into burger_ingredient (burger_id, ingredient_id) values (4, 2);
insert into burger_ingredient (burger_id, ingredient_id) values (4, 3);
insert into burger_ingredient (burger_id, ingredient_id) values (4, 4);
insert into burger_ingredient (burger_id, ingredient_id) values (4, 5);
```

After that, run the `java -jar target / burgerly.jar` command to execute the application. By default, it will run on port `8080`.

## Running tests
After creating the database and its default records, run the `mvn test` command to start the automated tests.
