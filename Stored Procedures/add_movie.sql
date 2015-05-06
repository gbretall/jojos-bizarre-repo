CREATE DEFINER=`root`@`localhost` PROCEDURE `add_movie`(
IN movie_title varchar(100), movie_year int, movie_director varchar(100),
 movie_banner_url varchar(200), movie_trailer_url varchar(200), 
 star_firstname varchar(50), star_lastname varchar(50), 
 star_DOB date, star_photo_url varchar(200), genre_name varchar(32))
BEGIN
	DECLARE star_in_movie_id int;
    DECLARE new_movie_id int;
    DECLARE genre_in_movie_id int;
    
    CALL get_movie_id(movie_title, movie_year, new_movie_id);
    CALL get_star_id(star_firstname, star_lastname, star_DOB, star_in_movie_id);
    CALL get_genre_id(genre_name, genre_in_movie_id);
    
	SELECT star_in_movie_id;
   
	IF new_movie_id = -1 THEN
		INSERT INTO moviedb.movies (title, year, director, banner_url, trailer_url) 
		VALUES(movie_title, movie_year, movie_director, movie_banner_url, movie_trailer_url);
        CALL get_movie_id(movie_title, movie_year,new_movie_id);
    END IF;
    
    IF star_in_movie_id = -1 THEN 
		INSERT INTO moviedb.stars (first_name, last_name, DOB, photo_url) VALUES(star_firstname, star_lastname, star_DOB, star_photo_url);
        CALL get_star_id(star_firstname, star_lastname, star_DOB, star_in_movie_id);
	END IF;
    
    IF genre_in_movie_id = -1 THEN 
		INSERT INTO moviedb.genres (name) VALUES(genre_name);
        CALL get_genre_id(genre_name, genre_in_movie_id);
    END IF;

	INSERT INTO moviedb.stars_in_movies (star_id, movie_id) VALUES(star_in_movie_id, new_movie_id);
	INSERT INTO moviedb.genres_in_movies (genre_id, movie_id) VALUES(genre_in_movie_id, new_movie_id);
	END