CREATE DATABASE sampledb;


use  sampledb;

CREATE TABLE users_db 
(
  
  username VARCHAR(50) primary key,
  `password` VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  FirstName VARCHAR(50) NOT NULL,
  LastName VARCHAR(50) NOT NULL,
  Hobbies VARCHAR(50) 
);


