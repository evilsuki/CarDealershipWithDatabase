-- Create the database
USE sys;

DROP DATABASE IF EXISTS car_dealership;
CREATE DATABASE car_dealership;

USE car_dealership;

-- Create tables
CREATE TABLE dealerships (
	dealership_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL,
    PRIMARY KEY (dealership_id)
);

CREATE TABLE vehicles (
	vin VARCHAR(20) NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    color VARCHAR(20) NOT NULL,
    type VARCHAR(20) NOT NULL,
    year INT NOT NULL,
    miles INT NOT NULL,
    price DECIMAL(10, 2),
    sold BOOL NOT NULL DEFAULT(0),
    PRIMARY KEY (vin)
);

CREATE TABLE inventory (
	dealership_id INT NOT NULL,
    vin VARCHAR(20) NOT NULL,
    PRIMARY KEY (dealership_id, vin),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE sales_contracts (
	sales_id INT NOT NULL AUTO_INCREMENT,
    vin VARCHAR(20) NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(50) NOT NULL,
    sales_price DECIMAL(10, 2) NOT NULL,
    recording_fee DECIMAL(10,2) NOT NULL DEFAULT(100.00),
    processing_fee DECIMAL(10,2) NOT NULL DEFAULT(295.00),
    sales_tax DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (sales_id),
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE lease_contracts (
	lease_id INT NOT NULL AUTO_INCREMENT,
    vin VARCHAR(20) NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(50) NOT NULL,
    sales_price DECIMAL(10, 2) NOT NULL,
    ending_value DECIMAL(10,2) NOT NULL,
    lease_fee DECIMAL(10,2) NOT NULL DEFAULT(295.00),
    sales_tax DECIMAL(10,2) NOT NULL,
    monthly_payment DECIMAL(10,2) NOT NULL DEFAULT(0),
    PRIMARY KEY (lease_id),
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

-- Load Data 

INSERT INTO dealerships (name, address, phone) 
VALUES ('ABC Auto Sales', '123 Main St, Anytown, CA 12345', '555-123-4567'),
	   ('XYZ Motors', '456 Elm St, Springfield, NY 67890', '555-987-6543'),
	   ('Sunset Auto Group', '789 Oak Ave, Sunnyville, FL 54321', '555-456-7890'),
	   ('City Auto Mall', '321 Maple Rd, Metropolis, TX 98765', '555-789-0123'),
	   ('Golden Wheels Inc.', '987 Pine St, Mountainview, WA 23456', '555-321-6789');

        
INSERT INTO vehicles (vin, make, model, color, type, year, miles, price, sold) 
VALUES ('1HGCM82633A123456', 'Honda', 'Accord', 'Silver', 'Sedan', 2010, 80000, 9000.00, 1),
	   ('5XYZU3LB7DG025874', 'Hyundai', 'Santa Fe', 'Blue', 'SUV', 2015, 60000, 13000.00, 1),
	   ('3GNAL4EK4DS567890', 'Chevrolet', 'Equinox', 'Black', 'SUV', 2013, 70000, 11000.00, 1),
	   ('WAUFFAFL3BN123456', 'Audi', 'A4', 'White', 'Sedan', 2011, 85000, 9500.00, 1),
	   ('KM8JT3AB2DU123456', 'Kia', 'Sportage', 'Red', 'SUV', 2014, 55000, 12000.00, 1),
	   ('1HGCM82634A123457', 'Honda', 'Civic', 'Black', 'Sedan', 2012, 75000, 8000.00, 1),
	   ('5XYZU3LB8DG025875', 'Hyundai', 'Elantra', 'Silver', 'Sedan', 2016, 40000, 14000.00, 1),
	   ('3GNAL4EK5DS567891', 'Chevrolet', 'Malibu', 'Blue', 'Sedan', 2014, 60000, 10000.00, 0),
	   ('WAUFFAFL4BN123457', 'Audi', 'Q5', 'Gray', 'SUV', 2015, 70000, 16000.00, 0),
	   ('KM8JT3AB3DU123457', 'Kia', 'Sorento', 'White', 'SUV', 2017, 35000, 18000.00, 0),
	   ('1HGCM82635A123458', 'Honda', 'Accord', 'Silver', 'Sedan', 2011, 90000, 8500.00, 0),
	   ('5XYZU3LB9DG025876', 'Hyundai', 'Tucson', 'Red', 'SUV', 2013, 55000, 12000.00, 0),
	   ('3GNAL4EK6DS567892', 'Chevrolet', 'Cruze', 'Black', 'Sedan', 2012, 80000, 9500.00, 0),
	   ('WAUFFAFL5BN123458', 'Audi', 'A6', 'White', 'Sedan', 2014, 65000, 13000.00, 0),
	   ('KM8JT3AB4DU123458', 'Kia', 'Optima', 'Blue', 'Sedan', 2016, 45000, 15000.00, 0),
	   ('1HGCM82636A123459', 'Honda', 'Civic', 'Gray', 'Sedan', 2013, 70000, 8500.00, 0),
	   ('5XYZU3LBAFG025877', 'Hyundai', 'Sonata', 'Silver', 'Sedan', 2017, 40000, 14000.00, 0),
	   ('3GNAL4EK7DS567893', 'Chevrolet', 'Impala', 'Red', 'Sedan', 2015, 60000, 11000.00, 0),
	   ('WAUFFAFL6BN123459', 'Audi', 'Q7', 'Black', 'SUV', 2016, 70000, 16000.00, 0),
	   ('KM8JT3AB5DU123459', 'Kia', 'Forte', 'White', 'Sedan', 2018, 35000, 18000.00, 0),
	   ('1HGCM82637A123460', 'Honda', 'Accord', 'Silver', 'Sedan', 2012, 80000, 9000.00, 0),
	   ('5XYZU3LBBFG025878', 'Hyundai', 'Elantra', 'Blue', 'Sedan', 2016, 40000, 13000.00, 0),
	   ('3GNAL4EK8DS567894', 'Chevrolet', 'Malibu', 'Black', 'Sedan', 2014, 60000, 10000.00, 0),
	   ('WAUFFAFL7BN123460', 'Audi', 'A4', 'Gray', 'Sedan', 2015, 70000, 15000.00, 0),
	   ('KM8JT3AB6DU123460', 'Kia', 'Sportage', 'Red', 'SUV', 2017, 35000, 17000.00, 0);

        
INSERT INTO inventory(dealership_id, vin)
VALUES  (1, '1HGCM82633A123456'),
		(2, '5XYZU3LB7DG025874'),
		(3, '3GNAL4EK4DS567890'),
		(4, 'WAUFFAFL3BN123456'),
		(5, 'KM8JT3AB2DU123456'),
		(1, '1HGCM82634A123457'),
		(2, '5XYZU3LB8DG025875'),
		(3, '3GNAL4EK5DS567891'),
		(4, 'WAUFFAFL4BN123457'),
		(5, 'KM8JT3AB3DU123457'),
		(1, '1HGCM82635A123458'),
		(2, '5XYZU3LB9DG025876'),
		(3, '3GNAL4EK6DS567892'),
		(4, 'WAUFFAFL5BN123458'),
		(5, 'KM8JT3AB4DU123458'),
		(1, '1HGCM82636A123459'),
		(2, '5XYZU3LBAFG025877'),
		(3, '3GNAL4EK7DS567893'),
		(4, 'WAUFFAFL6BN123459'),
		(5, 'KM8JT3AB5DU123459'),
		(1, '1HGCM82637A123460'),
		(2, '5XYZU3LBBFG025878'),
		(3, '3GNAL4EK8DS567894'),
		(4, 'WAUFFAFL7BN123460'),
		(5, 'KM8JT3AB6DU123460');
        
INSERT INTO sales_contracts (vin, customer_name, customer_email, sales_price, processing_fee, sales_tax) 
VALUES  ('1HGCM82633A123456', 'John Doe', 'johndoe@example.com', 9000.00, 295, 720.00),
		('5XYZU3LB7DG025874', 'Jane Smith', 'janesmith@example.com', 13000.00, 495, 1040.00),
		('3GNAL4EK4DS567890', 'Michael Johnson', 'michaeljohnson@example.com', 11000.00, 495, 880.00),
		('WAUFFAFL3BN123456', 'Emily Davis', 'emilydavis@example.com', 9500.00, 295, 760.00),
		('KM8JT3AB2DU123456', 'David Wilson', 'davidwilson@example.com', 12000.00, 495, 960.00),
		('1HGCM82634A123457', 'Sarah Thompson', 'sarahthompson@example.com', 8000.00, 295, 640.00),
		('5XYZU3LB8DG025875', 'Christopher Brown', 'christopherbrown@example.com', 14000.00, 495, 1120.00);
        
INSERT INTO lease_contracts (vin, customer_name, customer_email, sales_price, ending_value, sales_tax) 
VALUES  ('WAUFFAFL4BN123457', 'Lisa Anderson', 'lisaanderson@example.com', 16000.00, 8000.00, 1280.00),
		('KM8JT3AB3DU123457', 'Brian Wilson', 'brianwilson@example.com', 18000.00, 9000.00, 1440.00),
		('3GNAL4EK5DS567891', 'Jessica Martinez', 'jessicamartinez@example.com', 10000.00, 5000.00, 800.00),
		('1HGCM82635A123458', 'Daniel Johnson', 'danieljohnson@example.com', 13000.00, 6500.00, 1040.00),
		('5XYZU3LB9DG025876', 'Jennifer Thompson', 'jenniferthompson@example.com', 12000.00, 6000.00, 960.00);


-- CREATE PROCEDURES

DELIMITER //
CREATE PROCEDURE VehicleByPriceRange
(
	IN min_price DECIMAL(10,2),
	IN max_price DECIMAL(10,2),
    IN dealership_id INT
)
BEGIN
	IF min_price IS NOT NULL AND max_price IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE price BETWEEN min_price AND max_price
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY price;
	ELSEIF min_price IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE price >= min_price
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY price;
	ELSEIF max_price IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE price <= max_price
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY price;
	ELSE
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE v.sold = 0
			AND d.dealership_id = dealership_id
		ORDER BY price;
	END IF;
END //
DELIMITER;




DELIMITER //
CREATE PROCEDURE VehicleByMakeModel
(
	IN make_param VARCHAR(50),
	IN model_param VARCHAR(50),
    IN dealership_id INT
)
BEGIN
    IF make_param IS NOT NULL AND model_param IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE make = make_param
			AND model = model_param
            AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY make;
	ELSEIF make_param IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE make = make_param
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY make;
	ELSEIF model_param IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE model = model_param
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY make;
	ELSE
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE v.sold = 0
			AND d.dealership_id = dealership_id
		ORDER BY make;
    END IF;
END //
DELIMITER;



DELIMITER //
CREATE PROCEDURE VehicleByYearRange
(
	IN min_year INT,
	IN max_year INT,
    IN dealership_id INT
)
BEGIN
	IF min_year IS NOT NULL AND max_year IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE year BETWEEN min_year AND max_year
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY year;
	ELSEIF min_year IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE year >= min_year
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY year;
	ELSEIF max_year IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE year <= max_year
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY year;
	ELSE
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE v.sold = 0
			AND d.dealership_id = dealership_id
		ORDER BY year;
	END IF;
END //
DELIMITER;




DELIMITER //
CREATE PROCEDURE VehicleByColor
(
    IN color_param VARCHAR(20),
    IN dealership_id INT
)
BEGIN
    SELECT v.*
	FROM vehicles AS v
	JOIN inventory AS i
		ON i.vin = v.vin
	JOIN dealerships AS d
		ON i.dealership_id = d.dealership_id
	WHERE color = color_param
		AND v.sold = 0
        AND d.dealership_id = dealership_id;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE VehicleByMileRange
(
    IN min_miles INT,
    IN max_miles INT,
    IN dealership_id INT
)
BEGIN
    IF min_miles IS NOT NULL AND max_miles IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE miles BETWEEN min_miles AND max_miles
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY miles;
	ELSEIF min_miles IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE year >= min_miles
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY miles;
	ELSEIF max_miles IS NOT NULL THEN
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE year <= max_miles
			AND v.sold = 0
            AND d.dealership_id = dealership_id
		ORDER BY miles;
	ELSE
		SELECT v.*
		FROM vehicles AS v
		JOIN inventory AS i
			ON i.vin = v.vin
		JOIN dealerships AS d
			ON i.dealership_id = d.dealership_id
		WHERE v.sold = 0
			AND d.dealership_id = dealership_id
		ORDER BY miles;
	END IF;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE VehicleByType
(
    IN type_param VARCHAR(20),
    IN dealership_id INT
)
BEGIN
    SELECT v.*
	FROM vehicles AS v
	JOIN inventory AS i
		ON i.vin = v.vin
	JOIN dealerships AS d
		ON i.dealership_id = d.dealership_id
    WHERE type = type_param
		AND d.dealership_id = dealership_id
		AND v.sold = 0;
END //
DELIMITER ; 



DELIMITER //
CREATE PROCEDURE VehiclesInsert
(
    IN vin_param VARCHAR(20),
    IN make_param VARCHAR(50),
    IN model_param VARCHAR(50),
    IN color_param VARCHAR(20),
    IN type_param VARCHAR(20),
    IN year_param INT,
    IN miles_param INT,
    IN price_param DECIMAL(10, 2),
    IN sold_param BOOL,
    IN dealership_id_param INT
)
BEGIN
    INSERT INTO vehicles (vin, make, model, color, type, year, miles, price, sold)
    VALUES (vin_param, make_param, model_param, color_param, type_param, year_param, miles_param, price_param, sold_param);

    INSERT INTO inventory (dealership_id, vin)
    VALUES (dealership_id_param, vin_param);
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE AddLeaseContract(
    IN p_vin VARCHAR(20),
    IN p_customer_name VARCHAR(50),
    IN p_customer_email VARCHAR(50),
    IN p_sales_price DECIMAL(10, 2),
    IN p_ending_value DECIMAL(10, 2),
    IN p_sales_tax DECIMAL(10, 2)
)
BEGIN
    INSERT INTO lease_contracts (vin, customer_name, customer_email, sales_price, ending_value, sales_tax)
    VALUES (p_vin, p_customer_name, p_customer_email, p_sales_price, p_ending_value, p_sales_tax);
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE AddSalesContract(
    IN p_vin VARCHAR(20),
    IN p_customer_name VARCHAR(50),
    IN p_customer_email VARCHAR(50),
    IN p_sales_price DECIMAL(10, 2),
    IN p_processing_fee DECIMAL(10, 2),
    IN p_sales_tax DECIMAL(10, 2)
)
BEGIN
    INSERT INTO sales_contracts (vin, customer_name, customer_email, sales_price, processing_fee, sales_tax)
    VALUES (p_vin, p_customer_name, p_customer_email, p_sales_price, p_processing_fee, p_sales_tax);
END //
DELIMITER ;
