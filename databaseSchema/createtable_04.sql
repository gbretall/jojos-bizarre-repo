DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE moviedb;
USE moviedb;

CREATE TABLE movies
(
	id 				INT NOT NULL UNIQUE AUTO_INCREMENT,
    title 			VARCHAR(100) NOT NULL,
    year 			INT NOT NULL,
    director 		VARCHAR(100) NOT NULL,
    banner_url 		VARCHAR(200),
    trailer_url 	VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE stars 
(
	id 				INT NOT NULL UNIQUE AUTO_INCREMENT,
    first_name 		VARCHAR(50) NOT NULL,
    last_name 		VARCHAR(50) NOT NULL,
    DOB 			date,
    photo_url 		VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE stars_in_movies
(
	star_id 		INT NOT NULL,
    movie_id 		INT NOT NULL,
    PRIMARY KEY (star_id, movie_id),
    FOREIGN KEY (star_id) REFERENCES stars (id) ON delete cascade,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DElete cascade
);

CREATE TABLE genres 
(
	id 				INT NOT NULL UNIQUE AUTO_INCREMENT,
    name 			VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE genres_in_movies
(
	genre_id 			INT NOT NULL,
    movie_id 			INT NOT NULL,
    PRIMARY KEY (genre_id, movie_id),
    FOREIGN KEY (genre_id) REFERENCES genres (id) on delete cascade,
    FOREIGN KEY (movie_id) REFERENCES movies (id) on delete cascade
);


CREATE TABLE creditcards
(
	id 					VARCHAR(20) NOT NULL,
    first_name 			VARCHAR(50) NOT NULL,
    last_name 			VARCHAR(50) NOT NULL,
    expiration 			DATE NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE customers
(
	id 					INT NOT NULL UNIQUE AUTO_INCREMENT,
    first_name 			VARCHAR(50) NOT NULL,
    last_name 			VARCHAR(50) NOT NULL,
    cc_id 				VARCHAR(20),
    address 			VARCHAR(200) NOT NULL,
    email 				VARCHAR(50) NOT NULL,
    password 			VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cc_id) REFERENCES creditcards (id) on delete cascade
);

CREATE TABLE sales
(
	id 					INT NOT NULL UNIQUE AUTO_INCREMENT,
    customer_id 		INT NOT NULL,
    movie_id 			INT,
    sale_date 			date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers (id) on delete cascade,
    FOREIGN KEY (movie_id) REFERENCES movies (id) 		on delete cascade
);

CREATE TABLE employees (
	employee_email		varchar(50) primary key,
    employee_password	varchar(20) not null,
    employee_fullname	varchar(100)
);
