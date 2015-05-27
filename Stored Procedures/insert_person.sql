CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_person`(IN person_name VARCHAR(61) CHARACTER SET utf8)
BEGIN
IF NOT EXISTS (SELECT id FROM bookdb.tbl_people WHERE name = person_name)
THEN INSERT INTO bookdb.tbl_people (name) VALUES(person_name);
END IF;


END