package com.example.refactorings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class RefactoringTest {
    @Test
    @DisplayName("change signature")
    void changeSignature() {
        // use height, width, length when calculating volume
        Cage cage = new Cage(1.0, 2.0);
        Assertions.assertEquals(2.0, cage.getVolume());
    }

    interface Inf {
        int publicMethod();
    }

    class Class {
        public Inf method() {
            final int i = 0;
            return new Inf() {
                public int publicMethod() {
                    return i;
                }
            };
        }
    }

    @Test
    @DisplayName("convert anonymous to inner class")
    void convertAnonymousToInnerClass() {
    }

    @Test
    @DisplayName("Convert to instance method")
    void convertToInstanceMethod() {
        new ConvertToInstanceMethod();
    }

    @Test
    @DisplayName("encapsulate fields")
    void encapsulateFields() {
        class YourClass {
            public String aString;
        }
    }

    @Test
    @DisplayName("introduce constants")
    void introduceConstants() {
        List<String> strings = new ArrayList<>();
        strings.add("string");
        strings.add("string");
    }

    @Test
    @DisplayName("introduce field")
    void introduceField() {
        class AnotherClass {
            public int intValue() {
                throw new UnsupportedOperationException();
            }
        }

        class Class {
            AnotherClass anotherClass;

            public void method1() {
                int a = 1;

                //  anotherClass.intValue() is duplicated
                int b = a + anotherClass.intValue();
                int c = b + anotherClass.intValue();
            }
        }
    }

    @Test
    @DisplayName("extract interface")
    void extractInterface() {
        class AClass {
            public static final double CONSTANT = 3.14;

            public void publicMethod() {//some code here
            }

            public void secretMethod() {//some code here}
            }
        }
    }

    @Test
    @DisplayName("extract method")
    void extractMethod() {
        new ExtractMethodSample();
    }

    @Test
    @DisplayName("extract superclass")
    void extractSuperclass() {
        class MyClass {
            public int varInt;
            private double varDouble;
            public static final int CONSTANT = 0;

            // should be abstract in super class
            public void publicMethod() {
            }

            public void hiddenMethod() {
            }

            public void setVarDouble(double var) {
                this.varDouble = var;
            }

            public double getVarDouble() {
                return varDouble;
            }
        }
    }

    @Test
    @DisplayName("introduce variable")
    void extractIntroduceVariable() {
        Long anotherClass = 1l;
        int a = 1;
        // duplicated
        int b = a + anotherClass.intValue();
        int c = b + anotherClass.intValue();
    }

    @Test
    @DisplayName("introduce parameter")
    void extractParameter() {
        class HelloWorldPrinter {
            public void print() {
                System.out.println(generateText());
            }

            private String generateText() {
                // message
                return "Hello, World!".toUpperCase();
            }
        }
    }

    @Test
    @DisplayName("extract delegate")
    void extractDelegate() {
        new ExtractDelegateSample();
    }

    @Test
    @DisplayName("extract method object")
    void extractMethodObject() {
        sort(Arrays.asList(1, 9, 3, 5));
    }

    private List<Integer> sort(List<Integer> list) {
        List<Integer> sorted = new ArrayList<>();
        if (list.size() == 0)
            return list;
        else {
            // move declaraton of X closer to usages
            List<Integer> l = new ArrayList<>();
            int m = list.get(0);
            List<Integer> h = new ArrayList<>();
            for (int i : list.subList(1, list.size())) {
                if (i < m)
                    l.add(i);
                else
                    h.add(i);
            }
            if (l != null) sorted.addAll(sort(l));
            sorted.add(m);
            if (h != null) sorted.addAll(sort(h));
        }
        return sorted;
    }

    @Test
    @DisplayName("replace conditional logic with strategy pattern")
    void replaceConditionalLogicWithStrategyPattern() {
        ConditionalLogicExistingClass calculator = new ConditionalLogicExistingClass();
        Assertions.assertEquals(1_825, calculator.calculateInsurance(5_000), "low");
        Assertions.assertEquals(38_600, calculator.calculateInsurance(25_000), "medium");
        Assertions.assertEquals(78_500, calculator.calculateInsurance(50_000), "high");
        Assertions.assertEquals(106_400, calculator.calculateInsurance(100_000), "very high");
        /**
         * 1. Extract Method Object: class로 추출. InsuranceCalculator
         * 2. Extract Methods: getAdjustment, getWeight, getConstants
         *      - template method primitive operations
         * 3. factory method of 제공
         *      - 서브 클래스들을 필요하게 만듦
         * 4. push members down(primitive operations)
         * 5. coverage를 보면서 unused code 삭제
         */
    }

    @Test
    @DisplayName("generify refactoring  transform existing code that does not use Generics, into the Generics-aware code ")
    void generifyRefactoringTransformExistingCodeThatDoesNotUseGenericsIntoTheGenericsAwareCode() {
        List list = new LinkedList();
        list.add("string");
    }

    @Test
    @DisplayName("inline constructor, superclass, anonymous class")
    void inlineVariableMethodConstructorSuperclassAnonymousClass() {
        class Class {
            public int varInt;

            // inline constructor
            public Class() {
                this(0);
            }

            public Class(int i) {
                varInt = i;
            }

            public void method() {
                Class aClass = new Class();
            }
        }

        class Bar {
            int calculations1() {
                return 0;
            }

            int calculations2() {
                return 0;
            }
        }

        // inline superclass(refactor at Bar)
        class Foo extends Bar {
            int someMethod() {
                int something = 0;
                if (something > calculations1()) {
                    return calculations2();
                }
                return -1;
            }
        }

        class MyComparator implements Comparator<String> {
            @Override
            public int compare(String s1, String s2) {
                return 0;
            }
        }

        List<String> scores = new ArrayList();
        // inline anonymous class
        scores.sort(new MyComparator());
    }

    @Test
    @DisplayName("invert boolean")
    void invertBoolean() {
        System.out.println(method(0));
    }

    // invert boolean
    boolean method(int a) {
        if (a > 15 && a < 100) {
            return true;
        }
        return false;
    }

    @Test
    @DisplayName("make static")
    void makeStatic() {
        new MakeStaticSample();
    }

    @Test
    @DisplayName("migrate")
    void migrate() {
        // migrate / edit
    }

    @Test
    @DisplayName("copy class and move method")
    void copyAndMove() {
        new CopyClassSample();
    }

    @Test
    @DisplayName("pull members up")
    void pullMembersUp() {
        new PullMembersUpSample();
    }

    @Test
    @DisplayName("remove middleman")
    void removeMiddleman() {
        new RemoveMiddlemanSample();
    }

    @Test
    @DisplayName("replace constructor with builder")
    void replaceConstructorWithBuilder() {
        new ReplaceConstructorWithBuilderSample("Red Delicious").saying();
    }

    @Test
    @DisplayName("replace constructor with factory method")
    void replaceConstructorWithFactoryMethod() {
        new ReplaceConstructorWithBuilderSample("Red Delicious").saying();
    }

    @Test
    @DisplayName("replace inheritance with delegation")
    void replaceInheritanceWithDelegation() {
        new ReplaceInheritanceWithDelegationSample();
    }

    @Test
    @DisplayName("replace temp with query")
    void replaceTempWithQuery() {
        printConcatedString("str");
    }

    void printConcatedString(String str) {
        String originalString = "Original String ";
        String result = originalString.concat(str);
        System.out.println(result);
    }

    @Test
    @DisplayName("type migration")
    void typeMigration() {
        someMethodToTypeMigrate(0);
    }

    void someMethodToTypeMigrate(int i) {
    }

    @Test
    @DisplayName("wrap return value")
    void wrapReturnValue() {
    }

    class Order {
        String customer;

        String getCustomer() {
            return customer;
        }
    }
}
