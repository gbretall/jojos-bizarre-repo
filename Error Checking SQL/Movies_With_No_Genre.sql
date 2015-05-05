select *
from moviedb.movies M left join moviedb.genres_in_movies GIM
on M.id = GIM.movie_id
where GIM.genre_id is null