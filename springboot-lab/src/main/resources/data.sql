-- Sample categories
INSERT INTO category (id, name) VALUES (1, 'Electronics');
INSERT INTO category (id, name, parent_id) VALUES (2, 'Phones', 1);
INSERT INTO category (id, name, parent_id) VALUES (3, 'Laptops', 1);

-- Sample products
INSERT INTO product (id, name, price, category_id) VALUES (1, 'iPhone 15', 1200, 2);
INSERT INTO product (id, name, price, category_id) VALUES (2, 'MacBook Pro', 2500, 3);
INSERT INTO product (id, name, price, category_id) VALUES (3, 'Samsung Galaxy S24', 1000, 2);

-- Sample orders
INSERT INTO `order` (id, order_date) VALUES (1, '2025-07-09T10:00:00');
INSERT INTO `order` (id, order_date) VALUES (2, '2025-07-09T11:00:00');

-- Sample order items
INSERT INTO order_item (id, quantity, price, order_id, product_id) VALUES (1, 2, 1200, 1, 1);
INSERT INTO order_item (id, quantity, price, order_id, product_id) VALUES (2, 1, 2500, 1, 2);
INSERT INTO order_item (id, quantity, price, order_id, product_id) VALUES (3, 1, 1000, 2, 3);
