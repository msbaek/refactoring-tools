package com.example.refactorings.ch11;

public abstract class Party {
    protected String name;

    public Party(String name) {
        this.name = name;
    }

    public abstract int getAnnualCost();

    public String getName() {
        return name;
    }
}
