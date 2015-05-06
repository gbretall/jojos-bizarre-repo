select	group_concat(distinct M1.id), M1.title
from	(moviedb.movies M1 
	inner join moviedb.movies M2 on M1.title like M2.title and M1.year=M2.year and M1.id != M2.id ) 
group by  M1.title;