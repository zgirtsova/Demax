package dtos.users;

import annotations.FieldName;

public class UserDTO {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;


    public UserDTO() {}

    @FieldName(fieldName = "id")
    public int getId() {
        return id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "email")
    public String getEmail() {
        return email;
    }

    @FieldName(fieldName = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    @FieldName(fieldName = "password")
    public String getPassword() {
        return password;
    }

    @FieldName(fieldName = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    @FieldName(fieldName = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @FieldName(fieldName = "first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @FieldName(fieldName = "last_name")
    public String getLastName() {
        return lastName;
    }

    @FieldName(fieldName = "last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @FieldName(fieldName = "role")
    public String getRole() {
        return role;
    }

    @FieldName(fieldName = "role")
    public void setRole(String role) {
        this.role = role;
    }


}