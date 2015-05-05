select *
from moviedb.genres G left join moviedb.genres_in_movies GIM
on G.id = GIM.genre_id
where GIM.Movie_id is null