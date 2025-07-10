-- Clear all tables to avoid duplicate key errors on repeated runs
DELETE FROM review;
DELETE FROM order_item;
DELETE FROM `orders`;
DELETE FROM product;
DELETE FROM category;
DELETE FROM customer;
DELETE FROM user;

-- Insert customers
INSERT INTO customer (id, name, email, password, address) VALUES (1, 'Alice', 'alice@example.com', 'password1', '123 Main St');
INSERT INTO customer (id, name, email, password, address) VALUES (2, 'Bob', 'bob@example.com', 'password2', '456 Oak Ave');

-- Insert users
INSERT INTO user (id, username) VALUES (1, 'admin');
INSERT INTO user (id, username) VALUES (2, 'staff');

-- Insert categories
INSERT INTO category (id, name) VALUES (1, 'Electronics');
INSERT INTO category (id, name) VALUES (2, 'Books');

-- Insert products
INSERT INTO product (id, name, price, category_id) VALUES (1, 'Smartphone', 699.99, 1);
INSERT INTO product (id, name, price, category_id) VALUES (2, 'Laptop', 1299.99, 1);
INSERT INTO product (id, name, price, category_id) VALUES (3, 'Novel', 19.99, 2);

-- Insert orders
INSERT INTO orders (id, order_date, customer_id, user_id) VALUES (1, '2025-07-08T10:00:00', 1, 1);
INSERT INTO orders (id, order_date, customer_id, user_id) VALUES (2, '2025-07-08T11:00:00', 2, 2);

-- Insert order items
INSERT INTO order_item (id, quantity, price, order_id, product_id) VALUES (1, 2, 699.99, 1, 1);
INSERT INTO order_item (id, quantity, price, order_id, product_id) VALUES (2, 1, 1299.99, 1, 2);
INSERT INTO order_item (id, quantity, price, order_id, product_id) VALUES (3, 3, 19.99, 2, 3);

-- Insert reviews
INSERT INTO review (id, rating, comment, product_id, customer_id) VALUES (1, 5, 'Great phone!', 1, 1);
INSERT INTO review (id, rating, comment, product_id, customer_id) VALUES (2, 4, 'Good laptop.', 2, 2);
INSERT INTO review (id, rating, comment, product_id, customer_id) VALUES (3, 3, 'Nice book.', 3, 1);
