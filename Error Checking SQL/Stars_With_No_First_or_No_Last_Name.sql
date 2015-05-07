SELECT S.id, S.last_name, S.first_name
FROM moviedb.stars S
WHERE S.last_name = "" OR S.first_name = "";

