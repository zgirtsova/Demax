package dtos.actions;

import annotations.FieldName;

import java.time.LocalDateTime;

public class ActionDTO {

    private int id;
    private LocalDateTime occuredOn;
    private int userId;
    private String action;

    public ActionDTO() {}

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
        return occuredOn;
    }

    @FieldName(fieldName = "occured_on")
    public void setOccuredOn(LocalDateTime occuredOn) {
        this.occuredOn = occuredOn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FieldName(fieldName = "action")
    public String getAction() {
        return action;
    }

    @FieldName(fieldName = "action")
    public void setAction(String action) {
        this.action = action;
    }

}