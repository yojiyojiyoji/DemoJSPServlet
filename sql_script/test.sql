/*
INSERT INTO products (productCode, name, version, releaseDate)
VALUES('CR001', 'Cardio Measurer', '1.0', '2011-02-15');
*/

SELECT * FROM products;

SELECT * FROM registrations;

SELECT * FROM technicians WHERE techID=11;

INSERT INTO technicians (firstName, lastName, email, phone, password)
VALUES('Joel', 'Murach', 'joel@murach.com', '415-123-4567', 'sesame');


SELECT * FROM customers WHERE customerID=1045;


UPDATE customers SET firstName = 'Yoji', lastName = 'Uno' WHERE customerID=1045;


SELECT * FROM customers WHERE email = 'kelly@example.com';



SELECT * FROM customers;

SELECT * FROM administrators;