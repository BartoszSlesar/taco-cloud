

CREATE TABLE IF NOT EXISTS `Taco` (
  `id` INTEGER  PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) not null,
  `created_at` timestamp not null
);

CREATE TABLE IF NOT EXISTS `Ingredient` (
 `id` varchar(4) not null PRIMARY KEY,
  `name` varchar(25) not null,
  `type` varchar(10) not null
);

CREATE TABLE IF NOT EXISTS `Taco_Ingredients` (
  `id` INTEGER  PRIMARY KEY AUTO_INCREMENT,
  `taco_id` INTEGER not null,
  `ingredient_id` varchar(4) not null
);



CREATE TABLE IF NOT EXISTS `Taco_Order` (
  `id` INTEGER  PRIMARY KEY AUTO_INCREMENT,
  `delivery_name` varchar(50) not null,
  `delivery_street` varchar(50) not null,
  `delivery_city` varchar(50) not null,
  `delivery_state` varchar(10) not null,
  `delivery_zip` varchar(15) not null,
  `credit_card` INTEGER not null,
  `placed_at` timestamp not null
);
--
CREATE TABLE IF NOT EXISTS `Credit_Card` (
   `id` INTEGER  PRIMARY KEY AUTO_INCREMENT,
   `card_holder` varchar(50) not null,
   `cc_number` varchar(16) not null,
   `cc_expiration` varchar(10) not null,
   `cc_cvv` varchar(3) not null
);

CREATE TABLE IF NOT EXISTS `Taco_Orders_Tacos` (
  `taco_order_id` INTEGER not null,
  `taco_id` varchar(4) not null
);

alter table Taco_Orders_Tacos add foreign key (taco_order_id) references Taco_Order(id);
alter table Taco_Orders_Tacos add foreign key (taco_id) references Taco(id);
alter table Taco_Order add foreign key (credit_card) references Credit_Card(id);
alter table Taco_Ingredients add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients add foreign key (ingredient_id) references Ingredient(id);


