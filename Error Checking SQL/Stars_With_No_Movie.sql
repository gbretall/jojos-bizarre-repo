select *
from moviedb.stars S left join moviedb.stars_in_movies SIM
on S.id = SIM.star_id
where SIM.star_id is null