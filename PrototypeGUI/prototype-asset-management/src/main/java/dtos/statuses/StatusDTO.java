package dtos.statuses;

public class StatusDTO {
    private String statusName;

    public StatusDTO(String statusName) {
        this.statusName = statusName;
    }

    private String getStatusName() {
        return statusName;
    }

    private void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}