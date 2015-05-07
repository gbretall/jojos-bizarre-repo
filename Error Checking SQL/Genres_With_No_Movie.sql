SELECT G.id, G.name
FROM moviedb.genres G LEFT JOIN moviedb.genres_in_movies GIM
ON G.id = GIM.genre_id
WHERE GIM.Movie_id IS NULL
ORDER BY G.id;