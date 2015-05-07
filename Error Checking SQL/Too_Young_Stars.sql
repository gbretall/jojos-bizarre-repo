SELECT S.id, S.first_name, S.last_name, S.DOB
FROM moviedb.stars	S
WHERE S.dob>curdate()
ORDER BY S.last_name, S.last_name;
