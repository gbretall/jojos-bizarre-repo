SELECT *
FROM moviedb.movies M LEFT JOIN moviedb.stars_in_movies SIM
ON M.id = SIM.movie_id
WHERE SIM.Movie_id IS NULL;