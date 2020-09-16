package com.example.refactorings;

class SuperBar {
    public void parentMethod() {
    }
}

public class ReplaceInheritanceWithDelegationSample extends SuperBar {
    public void hiddenMethod() {
    }
}
