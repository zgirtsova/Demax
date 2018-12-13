package util;

import java.sql.Timestamp;

public class Filter {
    public Timestamp from;
    public Timestamp to;
    public Integer statusId;
    public Integer projectId;
    public Byte sortRule;
    public Integer page;
    public Integer perPage;

    public Filter(Timestamp from, Timestamp to, Integer statusID, Integer projectID,Integer page,Integer perPage, Boolean firstColumn, Boolean secondColumn, Boolean priority){
        this.from = from;
        this.to = to;
        this.statusId = statusID;
        this.projectId = projectID;
        this.sortRule = getSortRule(firstColumn, secondColumn, priority);
        this.page = page;
        this.perPage = perPage;
    }
    //lookup the sortRule to use for first and second Columns true is ASC and false is DECS 
    //for priority True is first and False is second
    private Byte getSortRule(Boolean firstColumn, Boolean secondColumn, Boolean priority){
        if(firstColumn!=null && secondColumn!=null && priority!=null){
            if(firstColumn && secondColumn && priority){
                return 0;
            }else if(firstColumn && !secondColumn && priority){
                return 1;
            }else if(!firstColumn && secondColumn && priority){
                return 2;
            }else if(!firstColumn && !secondColumn && priority){
                return 3;
            }else if(firstColumn && secondColumn && !priority){
                return 4;
            }else if(!firstColumn && secondColumn && !priority){
                return 5;
            }else if(firstColumn && !secondColumn && !priority){
                return 6;
            }else if(!firstColumn && !secondColumn && !priority){
                return 7;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
    }

}