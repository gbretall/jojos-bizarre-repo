SELECT CC.*
FROM moviedb.creditcards  CC inner join moviedb.customers CUST on CC.id =CUST.cc_id
WHERE CC.expiration < curdate()