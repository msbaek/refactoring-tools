package com.example.refactorings;

public class MakeStaticSample {
    private void foo(int i) {
        bar(i);
    }

    private void bar(int i) {
        baz(i);
    }

    private void baz(int i) {
    }
}
