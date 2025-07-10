INSERT INTO categories (id, name) VALUES (1, 'Electronics');
INSERT INTO categories (id, name, parent_id) VALUES (2, 'Laptops', 1);

INSERT INTO products (id, name, price, stock, category_id) VALUES (1, 'Smartphone', 599.99, 50, 1);
INSERT INTO products (id, name, price, stock, category_id) VALUES (2, 'Gaming Laptop', 1299.99, 20, 2);
INSERT INTO products (id, name, price, stock, category_id) VALUES (3, 'Ultrabook', 999.99, 15, 2);
