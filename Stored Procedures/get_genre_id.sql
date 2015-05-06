CREATE DEFINER=`root`@`localhost` PROCEDURE `get_genre_id`(
IN genre_name varchar(32), INOUT genre_in_movie_id int)
BEGIN
IF NOT EXISTS 
	(SELECT id FROM moviedb.genres WHERE genre_name = name)
    THEN SET genre_in_movie_id = -1;
ELSE 
	SET genre_in_movie_id = (SELECT id 
    FROM moviedb.genres WHERE genre_name = name);
END IF;
-- SELECT genre_in_movie_id;

END