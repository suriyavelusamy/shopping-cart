# Shopping-cart
Shopping cart service to add, list, delete items and calculate cart value.

# Database
Use below command to create database locally using docker.

``docker run -it --rm  --name postgres_shopping_cart -d -p 5455:5432  
-v /Library/postgres_shopping_cart/data:/var/lib/postgresql/data 
-e POSTGRES_USER=postgres 
-e POSTGRES_PASSWORD=postgres 
-e POSTGRES_DB=shopping_cart_db 
postgres``

# Shipping Price
To find shipping price based on distance and weight, maintained the mapping seed data 
as flyway migration script(db/migration/V2__postal_distance_seed_data.sql).
