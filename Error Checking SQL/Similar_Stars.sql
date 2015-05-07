SELECT	GROUP_CONCAT(DISTINCT S1.id SEPARATOR ', '), S1.first_name, S1.last_name, S1.DOB
FROM	(moviedb.stars S1 
	INNER JOIN moviedb.stars S2 
    ON S1.first_name LIKE S2.first_name 
		AND S1.last_name LIKE S2.last_name
		AND S1.id != S2.id
		AND S1.DOB = S2.DOB) 
GROUP BY  S1.first_name, S1.last_name, S1.DOB;