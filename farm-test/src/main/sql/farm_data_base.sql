-- DROP DATABASE IF EXISTS farm_test_db;

CREATE DATABASE IF NOT EXISTS farm_test_db;
USE farm_test_db;

DROP TABLE IF EXISTS farm;
DROP TABLE IF EXISTS chickens;
DROP TABLE IF EXISTS eggs;

CREATE TABLE farm(
	id INT auto_increment PRIMARY KEY, 
	money INT,
    egg_capacity INT, 
    chicken_capacity INT,
    egg_price INT, 
    chicken_price INT
);

CREATE TABLE chickens (
		id INT auto_increment PRIMARY KEY,
        days_of_life INT,
		farm_id INT,
		FOREIGN KEY(farm_id) REFERENCES farm(id)
);

CREATE TABLE eggs (
		id INT auto_increment PRIMARY KEY,
        days_of_life INT,
		farm_id INT,
		FOREIGN KEY(farm_id) REFERENCES farm(id)
);

SELECT * FROM farm;
SELECT * FROM chickens;
SELECT * FROM eggs;
