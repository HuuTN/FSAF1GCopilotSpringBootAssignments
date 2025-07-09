

-- An SQL script to insert initial testing data for the database.

-- Clean up tables to avoid duplicate key errors
SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM review;
DELETE FROM order_item;
DELETE FROM orders;
DELETE FROM product;
DELETE FROM users;
DELETE FROM category;
SET FOREIGN_KEY_CHECKS = 1;
-- Create tables if not exist



-- Categories
INSERT INTO category (name, description, active, created_at, updated_at) VALUES
('Electronics', 'Electronic devices and gadgets', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Computers', 'Computers and accessories', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Smartphones', 'Mobile phones and accessories', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Books', 'Books and e-books', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Clothing', 'Apparel and fashion items', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Users
INSERT INTO users (username, email, password, first_name, last_name, address, phone, created_at, updated_at) VALUES
('john_doe', 'john@example.com', '$2a$10$rTm8bQu2tkuRVe0RX.KF8.Eq.uD4Figures8CU1QwGGK6PVpKe', 'John', 'Doe', '123 Main St', '555-0100', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('jane_smith', 'jane@example.com', '$2a$10$8K1p/a6G1r0YZr0Figures8CU1QwGGK6PVpKe', 'Jane', 'Smith', '456 Oak Ave', '555-0101', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('admin_user', 'admin@example.com', '$2a$10$Kp9/a6G1r0YZr0Figures8CU1QwGGK6PVpKe', 'Admin', 'User', '789 Pine St', '555-0102', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Products
INSERT INTO product (name, description, price, stock, image_url, active, category_id, created_at, updated_at) VALUES
('Laptop Pro', '15" laptop with high performance', 1299.99, 50, 'laptop.jpg', true, (SELECT id FROM category WHERE name = 'Computers'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Smartphone X', 'Latest smartphone with advanced features', 999.99, 100, 'phone.jpg', true, (SELECT id FROM category WHERE name = 'Smartphones'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Wireless Mouse', 'Ergonomic wireless mouse', 29.99, 200, 'mouse.jpg', true, (SELECT id FROM category WHERE name = 'Computers'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Programming Book', 'Learn programming from scratch', 49.99, 75, 'book.jpg', true, (SELECT id FROM category WHERE name = 'Books'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T-Shirt', 'Cotton t-shirt with modern design', 19.99, 300, 'tshirt.jpg', true, (SELECT id FROM category WHERE name = 'Clothing'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Orders
INSERT INTO orders (user_id, total_amount, status, shipping_address, created_at, updated_at) VALUES
((SELECT id FROM users WHERE username = 'john_doe'), 1329.98, 'DELIVERED', '123 Main St', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM users WHERE username = 'jane_smith'), 1049.98, 'CONFIRMED', '456 Oak Ave', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Order Items
INSERT INTO order_item (order_id, product_id, quantity, price, created_at, updated_at) VALUES
((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE username = 'john_doe')), (SELECT id FROM product WHERE name = 'Laptop Pro'), 1, 1299.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE username = 'john_doe')), (SELECT id FROM product WHERE name = 'Wireless Mouse'), 1, 29.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE username = 'jane_smith')), (SELECT id FROM product WHERE name = 'Smartphone X'), 1, 999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE username = 'jane_smith')), (SELECT id FROM product WHERE name = 'Programming Book'), 1, 49.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Reviews
INSERT INTO review (user_id, product_id, rating, comment, created_at, updated_at) VALUES
((SELECT id FROM users WHERE username = 'john_doe'), (SELECT id FROM product WHERE name = 'Laptop Pro'), 5, 'Excellent laptop, very fast!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM users WHERE username = 'jane_smith'), (SELECT id FROM product WHERE name = 'Smartphone X'), 4, 'Great phone, good camera', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM users WHERE username = 'john_doe'), (SELECT id FROM product WHERE name = 'Wireless Mouse'), 5, 'Very comfortable to use', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT id FROM users WHERE username = 'jane_smith'), (SELECT id FROM product WHERE name = 'Programming Book'), 4, 'Well written and easy to follow', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
