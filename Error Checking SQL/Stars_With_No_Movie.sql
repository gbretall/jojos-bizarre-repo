SELECT S.id, S.first_name, S.last_name
FROM moviedb.stars S LEFT JOIN moviedb.stars_in_movies SIM
ON S.id = SIM.star_id
WHERE SIM.star_id IS NULL