package entities;

import annotations.ColumnName;
import annotations.FieldName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Action {

    @ColumnName(columnName = "id")
    private int id;

    @ColumnName(columnName = "occured_on")
    private LocalDateTime occuredOn;

    @ColumnName(columnName = "action")
    private String action;

    @ColumnName(columnName = "user_id")
    private int userId;

    public Action() {
    }


    @FieldName(fieldName = "id")
    public int getId() {
        return id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "occured_on")
    public LocalDateTime getOccuredOn() {
        return this.occuredOn;
    }

    @FieldName(fieldName = "occured_on")
    public void setOccuredOn(LocalDateTime occuredOn) {
        this.occuredOn = occuredOn;
    }

    @FieldName(fieldName = "action")
    public String getAction() {
        return this.action;
    }

    @FieldName(fieldName = "action")
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
