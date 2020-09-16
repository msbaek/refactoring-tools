package com.example.refactorings;

public class ExtractDelegateSample {
    private String b;
    public String getInfo() {
        return ("(" + b + ")"); // delegatable logic(with b)
    }
}

class ExtractDelegateClient {
    ExtractDelegateSample delegate;
    String t2 = delegate.getInfo();
}
