select	group_concat(G1.id), G1.name
from	(moviedb.genres G1 
	inner join moviedb.genres G2 
    on G1.name like G2.name 
		and G1.id != G2.id) 
group by  G1.name;