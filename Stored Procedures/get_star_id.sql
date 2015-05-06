CREATE DEFINER=`root`@`localhost` PROCEDURE `get_star_id`(
IN star_first_name varchar(50), star_last_name varchar(50), star_DOB date, INOUT star_in_movie_id int)
BEGIN
IF NOT EXISTS 
	(SELECT id FROM moviedb.stars WHERE star_first_name = first_name AND star_last_name = last_name AND star_DOB = DOB)
    THEN SET star_in_movie_id = -1;
ELSE 
	SET star_in_movie_id = (SELECT id FROM moviedb.stars WHERE star_first_name = first_name 
							AND star_last_name = last_name AND star_DOB LIKE DOB);
END IF;

-- SELECT star_in_movie_id;

END