/* 1 */
select *
from products 
order by date_created desc
limit 5 offset 0;

/* 2 */
select *
from products 
where project_id = 1
order by date_created desc
limit 5 offset 0;

/* 3 */
select *
from products 
order by quantity asc
limit 5 offset 0;

/* 4 */
select *
from products 
where status_id = 1
order by quantity asc
limit 5 offset 0;

/* 5 */
select *
from products 
where date_created >= '2018-11-15' 
order by date_created desc
limit 5 offset 0;

/* 6 */
select *
from products 
where date_created >= '2018-11-15' and date_created <= '2018-11-21' 
order by date_created desc
limit 5 offset 0;

/* 7 */
select *
from products 
where date_created >= '2018-11-15' and date_created <= '2018-11-21' and project_id = 1 and status_id = 3
order by date_created desc
limit 5 offset 0;

/* 8 */
select *
from products 
where date_created >= '2018-11-15' and date_created <= '2018-11-21' and project_id = 1 and status_id = 3
order by quantity desc
limit 5 offset 0;


 