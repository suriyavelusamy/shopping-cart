create sequence hibernate_sequence start 1 increment 1;
create table shopping_cart.item (id int8 not null, product_desc varchar(255), product_id int8, quantity int4, primary key (id));
create table shopping_cart.shipping_weight_and_distance_mapping (id int8 not null, max_distance float8, max_weight float8, min_distance float8, min_weight float8, price float8, primary key (id));
