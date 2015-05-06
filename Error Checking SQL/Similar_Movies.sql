SELECT	GROUP_CONCAT(DISTINCT M1.id), M1.title
FROM	(moviedb.movies M1 
	INNER JOIN moviedb.movies M2 ON M1.title LIKE M2.title AND M1.year=M2.year AND M1.id != M2.id ) 
GROUP BY  M1.title;