package com.example.refactorings.humbleobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record EmailChangedEvent(long userId, String newEmail) {
}

public class User {
    private long userId;
    private String email;
    private UserType userType;
    private long companyId;
    private List<EmailChangedEvent> emailChangedEvents = new ArrayList<>();

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

    void changeEmail(String newEmail, Company company) {
        if (email.equals(newEmail)) {
            return;
        }

        UserType newUserType = company.isEmailCorporate(newEmail) ? UserType.Employee : UserType.Customer;
        if (userType() != newUserType) {
            int delta = newUserType == UserType.Employee ? 1 : -1;
            company.updateNoOfEmployees(delta);
        }
        email = newEmail;
        userType = newUserType;
        emailChangedEvents.add(new EmailChangedEvent(userId, newEmail));
    }

    public List<EmailChangedEvent> emailChangedEvents() {
        return Collections.unmodifiableList(emailChangedEvents);
    }
}