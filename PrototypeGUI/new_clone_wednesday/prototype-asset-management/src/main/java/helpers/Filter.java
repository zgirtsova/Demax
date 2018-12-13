package helpers;

import java.util.HashMap;

public class Filter<T> {

    public static final HashMap<String, String> columnNames = new HashMap<>() {{
        put(FilterNames.DATE_FROM, "date_created");
        put(FilterNames.DATE_TO, "date_created");
        put(FilterNames.STATUS_ID, "status_id");
        put(FilterNames.PROJECT_ID, "project_id");
    }};

    private String name;

    private String columnName;

    private T value;

    public String getColumnName() {
        return this.columnName;
    }

    private void setColumnName(String name) {
        this.columnName = columnNames.get(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.setColumnName(name);
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}