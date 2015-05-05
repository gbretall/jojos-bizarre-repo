select *
from moviedb.movies M left join moviedb.stars_in_movies SIM
on M.id = SIM.movie_id
where SIM.Movie_id is null