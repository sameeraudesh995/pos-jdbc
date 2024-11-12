CREATE DATABASE IF  NOT EXISTS jdbc;
       USE jdbc;
           CREATE TABLE IF NOT EXISTS customer(
               id INT PRIMARY KEY,
               name VARCHAR(45) NOT NULL,
               address VARCHAR(45) NOT NULL,
               salary DOUBLE
           );
USE  jdbc;
CREATE TABLE IF NOT EXISTS user(
    username VARCHAR(45) NOT NULL ,
    password VARCHAR(45) NOT NULL
);
INSERT INTO user VALUES ('Admin','1234');
select  * from user;