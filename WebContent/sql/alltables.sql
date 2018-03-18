delimiter //
DROP PROCEDURE IF EXISTS Instantiate;
CREATE PROCEDURE Instantiate()
BEGIN
	DROP TABLE IF EXISTS tb_comment;
	DROP TABLE IF EXISTS tb_blog;
	DROP TABLE IF EXISTS tb_follow;
	DROP TABLE IF EXISTS tb_user_hobby;
	DROP TABLE IF EXISTS tb_hobby;
	DROP TABLE IF EXISTS tb_user;
	DROP TABLE IF EXISTS tb_tag;
	
	CREATE TABLE tb_user
	(
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	PRIMARY KEY (username)

	);
	
	INSERT tb_user (
	
	username,
	email,
	password,
	firstname,
	lastname
	
	
	)
	
	VALUES
	('Tina','wli73@wisc.edu','1','Tina','li'),
	('Mary','wli73@wisc.edu','111','Mary','Smith'),
	('Vivian','vvaaaaa@gmail.edu','2','Vivian','Johnson'),
	('John','wli73@wisc.edu','1235555','John','Miller'),
	('Tom','gg3122@wayne.edu','122223','Tom','Martin'),
	('Anna','as3222@wayne.edu','877123','Anna','Moore'),
	('Jerry','liu9@wisc.edu','19023','Jerry','Taylor'),
	('James','weisshaar@wisc.edu','10023','James','Weisshaar'),
	('Somenath','somenath@harvard.edu','11123','Somenath','Garcia'),
	('Soni','Sonisheba32@wisc.edu','17723','Soni','sheba');
	
	
	CREATE TABLE tb_hobby
	(
	hobby_id INT NOT NULL AUTO_INCREMENT,
	hobbyname VARCHAR(50) NOT NULL,
	PRIMARY KEY (hobby_id)
	
	);
	
	
	INSERT tb_hobby (
	hobby_id,
	hobbyname
	)
	
	VALUES
	(1,'hiking'),
	(2,'swimming'),
	(3,'calligraphy'),
	(4,'bowling'),
	(5,'movie'),
	(6,'cooking'),
	(7,'dancing');
	
	CREATE TABLE tb_user_hobby(
	
	hobbyname VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	PRIMARY KEY(username, hobbyname)
	/*FOREIGN KEY (username) references tb_user (username),
	FOREIGN KEY (hobbyname) references tb_hobby (hobby_id)*/
	);
	
	INSERT tb_user_hobby (
	username,
	hobbyname
	)

	
	VALUES
	('Tina',1),
	('Mary',1),
	('Vivian',3),
	('Tom',2),
	('Jerry',7),
	('James',5),
	('Tom',4),
	('Somenath',5),
	('Anna',5),
	('Soni',6);
	
	
	CREATE TABLE tb_follow(
	
	follower_name VARCHAR(50) NOT NULL,
	following_name VARCHAR(50) NOT NULL,
	PRIMARY KEY(follower_name,following_name)
	FOREIGN KEY (follower_name) references tb_user (username),
	FOREIGN KEY (following_name) references tb_user (username)
	
	);
	
	INSERT tb_follow (
	follower_name,
	following_name
	)
	
	VALUES
	('Tina','Jerry'),
	('Wendy','Jerry'),
	('Jim','Tina'),
	('Tom','Tina'),
	('Jerry','Tina'),
	('Apple','Ben'),
	('Tom','Jerry'),
	('Somenath','Tina'),
	('Ken','Jerry'),
	('Ben','Tina');
	
	
	CREATE TABLE tb_blog(
	
	blog_id INT NOT NULL,
	username VARCHAR(50) NOT NULL,
	subject VARCHAR(200) NOT NULL,
	description VARCHAR(400) NOT NULL,
	blog_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(blog_id)
	/*FOREIGN KEY (username) references tb_user (username)
	*/
	
	);
	
	INSERT tb_blog (
	blog_id,
	username,
	subject,
	description,
	blog_date
	)
	
	VALUES
	(1,'Tina','blog 1 subject','blog 1 description','2016-01-01 00:00:01'),
	(2,'Tina','blog 2 subject','blog 2 description','2016-02-01 00:00:01'),
	(3,'Tina','blog 3 subject','blog 3 description','2016-03-01 00:00:01'),
	(4,'Jim','blog 4 subject','blog 4 description','2016-04-01 00:00:01'),
	(5,'Jim','blog 5 subject','blog 5 description','2016-05-01 00:00:01'),
	(6,'Jim','blog 6 subject','blog 6 description','2016-06-01 00:00:01'),
	(7,'Jerry','blog 7 subject','blog 7 description','2016-07-01 00:00:01'),
	(8,'Jerry','blog 8 subject','blog 8 description','2016-08-01 00:00:01'),
	(9,'Jerry','blog 9 subject','blog 9 description','2016-09-01 00:00:01'),
	(10,'Jerry','blog 10 subject','blog 10 description','2017-01-01 00:00:01');
	
	
	CREATE TABLE tb_comment(
	
	comment_id INT NOT NULL,
	blog_id INT NOT NULL,
	username VARCHAR(50) NOT NULL,
	sentiment BIT NOT NULL,
	description VARCHAR(200) NOT NULL,
	comment_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(comment_id)
	/*FOREIGN KEY (username) references tb_user (username),
	FOREIGN KEY (blog_id) references tb_blog (blog_id)
	*/
	
	);
	
	INSERT tb_comment (
	comment_id,
	username,
	blog_id,
	description,
	sentiment
	)
	
	VALUES
	(1,'Jerry',3,'comment 1 description',0),
	(2,'Jerry',3,'comment 2 description',0),
	(3,'Jerry',3,'comment 3 description',0),
	(4,'Tom',3,'comment 4 description',0),
	(5,'Tom',3,'comment 5 description',0),
	(6,'Tom',3,'comment 6 description',0),
	(7,'Somenath',3,'comment 7 description',0),
	(8,'Somenath',3,'comment 8 description',0),
	(9,'Somenath',3,'comment 9 description',0),
	(10,'Somenath',3,'comment 10 description',0);
	
	
	CREATE TABLE tb_tag(
	
	blog_id INT NOT NULL,
	tag VARCHAR(50) NOT NULL,
	PRIMARY KEY(blog_id, tag)
	/*FOREIGN KEY (blog_id) references tb_blog (blog_id)
	*/
	);
	
	INSERT tb_tag (
	blog_id,
	tag
	)
	
	VALUES
	(1,'tag for blog 1'),
	(2,'tag for blog 2'),
	(3,'tag for blog 3'),
	(4,'tag for blog 4'),
	(5,'tag for blog 5'),
	(6,'tag for blog 6'),
	(7,'tag for blog 7'),
	(8,'tag for blog 8'),
	(9,'tag for blog 9'),
	(10,'tag for blog 10');
	
	
END//