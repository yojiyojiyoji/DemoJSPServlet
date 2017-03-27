SELECT * FROM incidents;

INSERT INTO incidents(customerID, productCode, techID, dateOpened, dateClosed, title, description)
VALUES('1004', 'DRAFT10', null, '2013-07-07', null, 'Help!', 'I am Yoji ...');

SELECT * FROM incidents;



SELECT	i.incidentID, i.customerID, i.productCode, i.techID, i.dateOpened, i.dateClosed, i.title, i.description,
		c.email, c.firstName, c.lastName, 
		p.name,
		t.firstName, t.lastName, t.email
FROM incidents AS i
INNER JOIN customers AS c ON i.customerID = c.customerID
INNER JOIN products AS p ON i.productCode = p.productCode
LEFT OUTER JOIN technicians AS t ON i.techID = t.techID
;


SELECT	i.incidentID, i.customerID, i.productCode, i.techID, i.dateOpened, i.dateClosed, i.title, i.description,
		c.email, c.firstName, c.lastName, 
		p.name,
		t.firstName, t.lastName, t.email
FROM incidents AS i
INNER JOIN customers AS c ON i.customerID = c.customerID
INNER JOIN products AS p ON i.productCode = p.productCode
LEFT OUTER JOIN technicians AS t ON i.techID = t.techID
WHERE i.techID IS NULL;
;






SELECT t.techID, t.firstName, t.lastName, 
(SELECT COUNT(techID) FROM incidents i WHERE i.techID = t.techID) AS openIncidents
FROM technicians t;

SELECT t.techID, t.firstName, t.lastName, 
(SELECT COUNT(techID) FROM incidents i WHERE i.techID = t.techID AND i.dateClosed IS NULL) AS openIncidents
FROM technicians t;




SELECT	i.incidentID, i.customerID, i.productCode, i.techID, i.dateOpened, i.dateClosed, i.title, i.description,
		c.email, c.firstName, c.lastName, 
		p.name,
		t.firstName, t.lastName, t.email
FROM incidents AS i
INNER JOIN customers AS c ON i.customerID = c.customerID
INNER JOIN products AS p ON i.productCode = p.productCode
LEFT OUTER JOIN technicians AS t ON i.techID = t.techID
WHERE t.email='gunter@sportspro.com'
;


SELECT * FROM (SELECT * FROM incidents WHERE TechID IS NULL) AS unassigned
INNER JOIN customers ON customers.customerID = unassigned.customerID
INNER JOIN products ON products.productCode = unassigned.productCode
;


CREATE OR REPLACE VIEW incidents_view AS 
SELECT	i.incidentID, i.customerID, i.productCode, i.techID, i.dateOpened, i.dateClosed, i.title, i.description,
		c.email AS customerEmail, c.firstName AS customerFirstName, c.lastName AS customerLastName, 
		p.name AS productName,
		t.firstName AS technicianFirstName, t.lastName AS technicianLastName, t.email AS technicianEmail
FROM incidents AS i
INNER JOIN customers AS c ON i.customerID = c.customerID
INNER JOIN products AS p ON i.productCode = p.productCode
LEFT OUTER JOIN technicians AS t ON i.techID = t.techID
;

SELECT * FROM incidents_view WHERE technicianEmail='gunter@sportspro.com' AND dateClosed IS NULL;

SELECT * FROM incidents_view;

DROP VIEW incidents_view;


SELECT * FROM incidents_view WHERE incidentID=27;

UPDATE incidents_view 
SET techID = 11
WHERE incidentID = 36;

UPDATE incidents_view 
SET description = 'problem solved'
WHERE incidentID = 31;



SELECT * FROM incidents As i 
INNER JOIN customers AS c ON i.customerID = c.customerID
INNER JOIN products AS p ON i.productCode = p.productCode
LEFT OUTER JOIN technicians AS t ON i.techID = t.techID
;



/*
CREATE OR REPLACE VIEW test AS
(SELECT incidents.incidentID, incidents.customerID
FROM incidents)
;

SELECT * FROM test;

DROP VIEW test;
*/

/*
SELECT technicians.techID, technicians.firstName, technicians.lastName, COUNT(incidents.techID) 
FROM technicians
LEFT OUTER JOIN incidents ON technicians.techID=incidents.techID
GROUP BY incidents.techID
;
*/