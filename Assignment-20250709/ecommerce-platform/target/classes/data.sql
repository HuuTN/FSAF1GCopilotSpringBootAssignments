-- Oracle-compatible SQL script to insert 2 categories and 3 products into the database.
INSERT INTO categories (name) VALUES ('Electronics');

INSERT INTO categories (name) VALUES ('Books');

INSERT INTO products (name, price, stock, category_id, created_at, updated_at)
VALUES ('Smartphone', 699.99, 100, (SELECT id FROM categories WHERE name = 'Electronics'), SYSTIMESTAMP, SYSTIMESTAMP);

INSERT INTO products (name, price, stock, category_id, created_at, updated_at)
VALUES ('Laptop', 1299.99, 50, (SELECT id FROM categories WHERE name = 'Electronics'), SYSTIMESTAMP, SYSTIMESTAMP);

INSERT INTO products (name, price, stock, category_id, created_at, updated_at)
VALUES ('Java Programming Book', 39.99, 200, (SELECT id FROM categories WHERE name = 'Books'), SYSTIMESTAMP, SYSTIMESTAMP);

-- Write SQL script to insert data into the database for tabls: Orders, Order_Items.
INSERT INTO orders (user_id, customer_name, customer_email, total_amount, status, created_at, updated_at)
VALUES (1, 'John Doe', 'john@example.com', 1739.98, 'PENDING', SYSTIMESTAMP, SYSTIMESTAMP);

INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES ((SELECT id FROM orders WHERE user_id = 1 AND status = 'PENDING'), 
        (SELECT id FROM products WHERE name = 'Smartphone'), 1, 699.99);

INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES ((SELECT id FROM orders WHERE user_id = 1 AND status = 'PENDING'), 
        (SELECT id FROM products WHERE name = 'Laptop'), 1, 1299.99);

INSERT INTO orders (user_id, customer_name, customer_email, total_amount, status, created_at, updated_at)
VALUES (2, 'Jane Smith', 'jane@example.com', 39.99, 'COMPLETED', SYSTIMESTAMP, SYSTIMESTAMP);

INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES ((SELECT id FROM orders WHERE user_id = 2 AND status = 'COMPLETED'), 
        (SELECT id FROM products WHERE name = 'Java Programming Book'), 1, 39.99);



