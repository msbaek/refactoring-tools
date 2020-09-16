package com.example.refactorings;

class ClassA {
}

class ClassB {
}

class MyClass {
    ClassA classA = new ClassA();
    ClassB classB = new ClassB();

    // place cursor on method name
    static public void greatMethod(ClassA classA, ClassB classB) {
        System.out.println("classA = " + classA);
        System.out.println("classB = " + classB);
    }

    public void myMethod() {
        MyClass.greatMethod(classA, classB);
    }
}

public class ConvertToInstanceMethod {
}
