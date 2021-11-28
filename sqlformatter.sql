CREATE DATABASE db_utest;

USE db_utest;

CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  login VARCHAR(40) NOT NULL UNIQUE, 
  password VARCHAR(255) NOT NULL, 
  is_admin TINYINT(1) DEFAULT 0
);

CREATE TABLE unit_tests (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  name VARCHAR(50), 
  errors_number INT, 
  lead_time TIME, 
  created_at DATE
);

CREATE TABLE test_cases (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  name VARCHAR(80), 
  last_launch DATETIME, 
  is_complete TINYINT(1) DEFAULT 0, 
  framework VARCHAR(40)
);

INSERT INTO user(login, password, is_admin)
VALUES ("admin", "admin", 1);
