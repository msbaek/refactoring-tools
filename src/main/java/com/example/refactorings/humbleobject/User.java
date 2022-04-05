package com.example.refactorings.humbleobject;

public class User {
    private long userId;
    private String email;
    private UserType userType;
    private long companyId;

    public long companyId() {
        return this.companyId;
    }

    public String email() {
        return this.email;
    }

    public void email(String newEmail) {
        this.email = newEmail;
    }

    public void userType(UserType newUserType) {
        this.userType = newUserType;
    }

    public UserType userType() {
        return this.userType;
    }
}
