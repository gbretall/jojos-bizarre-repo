SELECT CC.id, CC.first_name, CC.last_name, CC.expiration
FROM moviedb.creditcards  CC inner join moviedb.customers CUST on CC.id =CUST.cc_id
WHERE CC.expiration < curdate()
ORDER BY CC.last_name, CC.first_name, CC.id;