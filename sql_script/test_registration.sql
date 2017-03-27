SELECT * FROM registrations
ORDER BY customerID;

SELECT firstName, lastName FROM customers WHERE email='kelly@example.com';

SELECT * FROM registrations AS r
INNER JOIN customers AS c ON r.customerID = c.customerID
INNER JOIN products AS p ON r.productCode = p.productCode
;


CREATE OR REPLACE VIEW registration_view AS
SELECT r.customerID AS customerID, r.productCode AS productCode, r.registrationDate AS registrationDate, 
c.firstName AS customerFirstName, c.lastName AS customerLastName, c.email AS customerEmail,
p.name AS productName
FROM registrations AS r
INNER JOIN customers AS c ON r.customerID = c.customerID
INNER JOIN products AS p ON r.productCode = p.productCode
;


SELECT * FROM registration_view WHERE customerEmail='kenzie@jobtrak.com';

SELECT * FROM registration_view;

DROP VIEW registration_view;



INSERT INTO registrations (customerID, productCode, registrationDate)
VALUES(1002, 'DRAFT10', '2013-07-07');

SELECT * FROM registrations WHERE customerID = 1002 AND productCode='DRAFT10';

DELETE FROM registrations 
WHERE customerID=1002 AND productCode='DRAFT10';



