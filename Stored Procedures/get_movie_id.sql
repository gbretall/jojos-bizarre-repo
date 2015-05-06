CREATE DEFINER=`root`@`localhost` PROCEDURE `get_movie_id`(
IN movie_title varchar(100), IN movie_year int, INOUT new_movie_id int)
BEGIN
IF 
NOT EXISTS(SELECT m.id FROM moviedb.movies m WHERE m.title = movie_title AND m.year = movie_year)
	THEN SET new_movie_id = -1;
ELSE 
	SET new_movie_id = (SELECT m.id FROM moviedb.movies m WHERE m.title = movie_title AND m.year = movie_year);
END IF;

-- SELECT new_movie_id;

END