package dtos.actions;

import java.time.LocalDateTime;

public class ActionDTO {
    private int id;
    private LocalDateTime date;
    private int userId;
    private String action;

    public ActionDTO(int id, LocalDateTime date, int userId, String action) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    private void setAction(String action) {
        this.action = action;
    }

}