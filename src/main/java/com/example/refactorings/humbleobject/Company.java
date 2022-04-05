package com.example.refactorings.humbleobject;

public class Company {
    private String domainName;
    private int noOfEmployees;

    public String domainName() {
        return this.domainName;
    }

    public int noOfEmployees() {
        return this.noOfEmployees;
    }

    public void noOfEmployees(int newNumbers) {
        this.noOfEmployees = newNumbers;
    }
}
