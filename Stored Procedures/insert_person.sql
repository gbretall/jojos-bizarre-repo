CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_person`(IN person_name VARCHAR(61) CHARACTER SET utf8)
BEGIN
IF NOT EXISTS (SELECT id FROM moviedb.tbl_people WHERE name = person_name)
THEN INSERT INTO moviedb.tbl_people (name) VALUES(person_name);
END IF;


END