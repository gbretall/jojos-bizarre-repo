select	group_concat(S1.id), S1.first_name, S1.last_name, S1.DOB
from	(moviedb.stars S1 
	inner join moviedb.stars S2 
    on S1.first_name like S2.first_name 
		and S1.last_name LIKE S2.last_name
		and S1.id != S2.id
		and S1.DOB = S2.DOB) 
group by  S1.first_name, S1.last_name, S1.DOB;