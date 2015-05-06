SELECT	GROUP_CONCAT(DISTINCT G1.id), G1.name
FROM	(moviedb.genres G1 
	INNER JOIN moviedb.genres G2 
    ON G1.name LIKE G2.name 
		AND G1.id != G2.id) 
GROUP BY  G1.name;