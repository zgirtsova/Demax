package helpers;

import java.lang.reflect.Method;

public class FieldData {

    private String fieldName;

    private String columnName;

    private Method getter;

    private Method setter;

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Method getGetter() {
        return this.getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return this.setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }
}
