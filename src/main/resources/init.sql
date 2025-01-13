create schema if not exists shop;
create table if not exists shop.products(
    prod_id bigint primary key,
    prod_name varchar(30) unique not null,
    prod_price decimal not null,
    prod_quantity int not null,
    prod_wholesale boolean not null
);

insert into shop.products(prod_name, prod_price, prod_quantity, prod_wholesale) values
('Milk', 1.07, 10, true),
('Cream 400g', 2.71, 20, true),
('Yogurt 400g', 2.10, 7, true),
('Packed potatoes 1kg', 1.47, 30, false),
('Packed cabbage 1kg', 1.19, 15 , false),
('Packed tomatoes 350g', 1.60, 50, false),
('Packed apples 1kg', 2.78, 18, false),
('Packed oranges 1kg', 3.20, 12, false),
('Packed bananas 1kg', 1.10, 25, true),
('Packed beef fillet 1kg', 12.8, 7, false),
('Packed pork fillet 1kg', 8.52, 14, false),
('Packed chicken breasts 1kgSour', 10.75, 8, false),
('Baguette 360g', 1.30, 10, true),
('Drinking water 1.5l', 0.80, 100, false),
('Olive oil 500ml', 5.30, 16, false),
('Sunflower oil 1l', 1.20, 12, false),
('Chocolate Ritter Sport 100g', 1.10, 50, true),
('Paulaner 0.5l', 1.10, 100, false),
('Whiskey Jim Beam 1l', 13.99, 39, false),
('Whiskey Jack Daniels 1l', 17.19, 20, false);

create table if not exists shop.discount_cards(
    card_id bigint primary key,
    card_number varchar(30) unique not null,
    discount_amount int not null
);

insert into shop.discount_cards(card_number, discount_amount) values
('1111', 3), ('2222', 3), ('3333', 4), ('4444', 5);