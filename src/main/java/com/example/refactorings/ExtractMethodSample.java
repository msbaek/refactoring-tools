package com.example.refactorings;

public class ExtractMethodSample {
    public ExtractMethodSample() {
    }

    void method1() {
        int a = 1;
        int b = 2;
        // add
        int c = a + b;
        int d = a + c;
    }
}
