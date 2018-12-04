SELECT  *
FROM    ( SELECT    ROW_NUMBER() OVER ( ORDER BY date_created DESC) AS RowNum, *
          FROM      products
          WHERE date_created > '2018-11-13' AND status_id = 3 AND project_id = 2
        ) AS RowConstrainedResult
WHERE   RowNum >= 1
    AND RowNum < 6
ORDER BY RowNum;

select * from projects;