CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_genre`(IN genre VARCHAR(20) CHARACTER SET utf8)
BEGIN
IF NOT EXISTS (SELECT id FROM bookdb.tbl_genres WHERE genre_name = genre)
THEN INSERT INTO bookdb.tbl_genres (genre_name) VALUES(genre);
END IF;


END