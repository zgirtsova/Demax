package entities;

import java.util.HashMap;
import java.util.Map;

import annotations.FieldName;

public class Status {
    public static final Map<Integer, String> statuses = new HashMap<>() {{
        put(1, "New");
        put(2, "In Progress");
        put(3, "Finished");
    }};

    @FieldName(columnName = "id")
    private int id;

    @FieldName(columnName = "status_type")
    private String statusType;

    public Status() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusType() {
        return this.statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
}
