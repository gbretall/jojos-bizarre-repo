SELECT C.id, C.email
FROM moviedb.customers C
WHERE C.email NOT LIKE "%@%"
ORDER BY C.id;