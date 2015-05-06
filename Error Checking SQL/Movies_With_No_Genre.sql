SELECT M.id, M.title
FROM moviedb.movies M LEFT JOIN moviedb.genres_in_movies GIM
ON M.id = GIM.movie_id
WHERE GIM.genre_id IS NULL