CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_publisher`(IN publisher VARCHAR(300) CHARACTER SET utf8)
BEGIN
IF NOT EXISTS (SELECT id FROM moviedb.tbl_publisher WHERE publisher_name = publisher)
THEN INSERT INTO moviedb.tbl_publisher (publisher_name) VALUES(publisher);
END IF;


END