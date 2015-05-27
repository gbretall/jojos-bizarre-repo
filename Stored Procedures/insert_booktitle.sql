CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_booktitle`(IN booktitle VARCHAR(300) CHARACTER SET utf8)
BEGIN
IF NOT EXISTS (SELECT id FROM bookdb.tbl_booktitle WHERE title = booktitle)
THEN INSERT INTO bookdb.tbl_booktitle (title) VALUES(booktitle);
END IF;


END