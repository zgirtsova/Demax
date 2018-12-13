package entities;

import annotations.ColumnName;
import annotations.FieldName;

public class User {

    @ColumnName(columnName = "id")
    private int id;

    @ColumnName(columnName = "email")
    private String email;

    @ColumnName(columnName = "password")
    private String password;

    @ColumnName(columnName = "first_name")
    private String firstName;

    @ColumnName(columnName = "last_name")
    private String lastName;

    @ColumnName(columnName = "role")
    private String role;

    public User() {}

    @FieldName(fieldName = "id")
    public int getId() {
        return this.id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "email")
    public String getEmail() {
        return this.email;
    }

    @FieldName(fieldName = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    @FieldName(fieldName = "password")
    public String getPassword() {
        return this.password;
    }

    @FieldName(fieldName = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    @FieldName(fieldName = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    @FieldName(fieldName = "first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @FieldName(fieldName = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    @FieldName(fieldName = "last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @FieldName(fieldName = "role")
    public String getRole() {
        return this.role;
    }

    @FieldName(fieldName = "role")
    public void setRole(String role) {
        this.role = role;
    }
}
