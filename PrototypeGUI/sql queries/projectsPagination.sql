/* 1 */
select prj.*, coalesce(t.count, 0 ) as c
from projects as prj left join (select project_id, count(id) as count from products group by project_id) as t on prj.id = t.project_id
order by date_created desc  
limit 5 offset 0;

/* 2 */
select prj.*, coalesce(t.count, 0 ) as c
from projects as prj left join (select project_id, count(id) as count from products group by project_id) as t on prj.id = t.project_id
where status_id = 1
order by date_created desc
limit 5 offset 5;

/* 3 */
select prj.*, coalesce(t.count, 0 ) as c
from projects as prj left join (select project_id, count(id) as count from products group by project_id) as t on prj.id = t.project_id
order by c desc
limit 5 offset 5;

/* 4 */
select prj.*, coalesce(t.count, 0 ) as c
from projects as prj left join (select project_id, count(id) as count from products group by project_id) as t on prj.id = t.project_id
where status_id = 1
order by c desc
limit 5 offset 5;

/* 5 */
select prj.*, coalesce(t.count, 0 ) as c
from projects as prj left join (select project_id, count(id) as count from products group by project_id) as t on prj.id = t.project_id
where status_id = 1 and date_created >= '2018-10-23'
order by c desc 
limit 5 offset 0;

 
