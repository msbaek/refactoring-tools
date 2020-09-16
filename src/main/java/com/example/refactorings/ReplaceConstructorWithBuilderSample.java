package com.example.refactorings;

public class ReplaceConstructorWithBuilderSample {
    private String name;

    public ReplaceConstructorWithBuilderSample(String name) {
        this.setName(name);
    }

    public void saying() {
        System.out.printf("On sale today : %s\n", getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
