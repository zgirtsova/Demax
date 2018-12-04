package entities;

import annotations.FieldName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Action {

    @FieldName(columnName = "id")
    private int id;

    @FieldName(columnName = "occured_on")
    private LocalDateTime occuredOn;

    @FieldName(columnName = "action")
    private String action;

    @FieldName(columnName = "user_id")
    private int userId;

    public Action() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOccuredOn() {
        return this.occuredOn;
    }

    public void setOccuredOn(Timestamp occuredOn) {
        if(occuredOn !=null){
            this.occuredOn = occuredOn.toLocalDateTime();
        }
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
