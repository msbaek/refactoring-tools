package com.example.refactorings;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Customer {
    public List<Customer> lists = new ArrayList<>();
    private Set<Order> orders = new HashSet<>();
    private String name;
    private static Customer instance;

    private Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public static Customer getInstance() {
        if (instance == null) {
            synchronized (Customer.class) {
                if (instance == null)
                    instance = new Customer();
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    Set friendOrders() {
        /** should only be used by Order when modifying the association */
        return orders;
    }

    public void addOrder(Order order) {
    }

    public int getDiscount() {
        throw new UnsupportedOperationException();
    }

    public boolean containsOrder(Order order) {
        return orders.contains(order);
    }
}

class Order {
    Customer getCustomer() {
        final List<Customer> lists = Customer.getInstance().lists;
        return lists.stream()
                    .filter(c -> c.containsOrder(this))
                    .findFirst()
                    .orElse(null);
    }

    public double getDiscountPrice(Customer customer) {
        return getGrossPrice() * (1 - customer.getDiscount());
    }

    private double getGrossPrice() {
        throw new UnsupportedOperationException();
    }
}

class Currency {
    private String code;

    public String getCode() {
        return code;
    }

    private Currency(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}

public class OrganizingDataTest {
    class Performance {
        private String name;
        private int wins;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(String wins) {
            this.wins = Integer.parseInt(wins);
        }
    }

    @Test
    void replaceArrayWithObject() {
        Performance row = new Performance();
        row.setName("Liverpool");
        row.setWins("15");

        String name = row.getName();
        int wins = row.getWins();
    }

    class SymFocus extends FocusAdapter implements Observer {
        private Interval subject;
        private TextField startField;
        private TextField endField;
        private TextField lengthField;

        public SymFocus() {
            subject = new Interval();
            subject.addObserver(this);
            update(subject, null);
        }

        @Override
        public void update(Observable o, Object arg) {
            startField.setText(subject.getStart());
            endField.setText(subject.getEnd());
            lengthField.setText(subject.getLength());
        }

        public void focusLost(FocusEvent event) {
            Object object = event.getSource();
            if (object == getStartField())
                StartField_FocusLost(event);
            else if (object == getEndField())
                EndField_FocusLost(event);
            else if (object == getLengthField())
                LengthField_FocusLost(event);
        }

        private void StartField_FocusLost(FocusEvent event) {
            if (isNotInteger(getStartField()))
                setStartField("0");
            calculateLength();
        }

        private void EndField_FocusLost(FocusEvent event) {
            if (isNotInteger(getEndField()))
                setEndField("0");
            calculateLength();
        }

        private void LengthField_FocusLost(FocusEvent event) {
            if (isNotInteger(getLengthField()))
                setLengthField("0");
            calculateEnd();
        }

        private boolean isNotInteger(String text) {
            throw new UnsupportedOperationException();
        }

        private void calculateLength() {
            try {
                int start = Integer.parseInt(getStartField());
                int end = Integer.parseInt(getEndField());
                int length = end - start;
                setLengthField(String.valueOf(length));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unexpected Number Format Error");
            }
        }

        private void calculateEnd() {
            try {
                int start = Integer.parseInt(getStartField());
                int length = Integer.parseInt(getLengthField());
                int end = start + length;
                setEndField(String.valueOf(end));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unexpected Number Format Error");
            }
        }

        public String getStartField() {
            return subject.getStart();
        }

        public void setStartField(String startField) {
            subject.setStart(startField);
        }

        public String getEndField() {
            return subject.getEnd();
        }

        public void setEndField(String endField) {
            subject.setEnd(endField);
        }

        public String getLengthField() {
            return subject.getLength();
        }

        public void setLengthField(String lengthField) {
            subject.setLength(lengthField);
        }
    }

    class Interval extends Observable {
        private String start;
        private String end;
        private String length;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
            super.setChanged();
            notifyObservers();
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }
    }

    @Test
    void name() {
        Long l = 1l;
        System.out.println(l.equals(null));
    }

    class IntRange {
        private int low;
        private int high;

        boolean includes(int arg) {
            return arg >= getLow() && arg <= getHigh();
        }

        void grow(int factor) {
            setHigh(getHigh() * factor);
        }

        IntRange(int low, int high) {
            low = low;
            high = high;
        }

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }
    }

    class PotentialEngeryCaculator {
        private double GRAVITATIONAL_CONSTANT = 9.81;

        double potentialEnergy(double mass, double height) {
            return mass * GRAVITATIONAL_CONSTANT * height;
        }
    }

    class Course {
        private final String name;
        private final boolean isAdvanced;

        Course(String name, boolean isAdvanced) {
            this.name = name;
            this.isAdvanced = isAdvanced;
        }

        public boolean isAdvanced() {
            return isAdvanced;
        }
    }

    class Person {
        private Set<Course> courses = new HashSet<>();

        public Set<Course> getCourses() {
            return Collections.unmodifiableSet(courses);
        }

        public void addCourse(Course course) {
            courses.add(course);
        }

        public void removeCourse(Course course) {
            courses.remove(course);
        }

        private long numberOfAdvancedCourse() {
            return getCourses()
                       .stream()
                       .filter(c -> c.isAdvanced() == true)
                       .count();
        }
    }

    @Test
    void course_person_client() {
        final Person kent = new Person();
        final Set<Course> courses = new HashSet<>();
        kent.addCourse(new Course("Smalltalk Programming", false));
        kent.addCourse(new Course("Appreciating Single Malts", true));
        assertThat(kent.getCourses().size()).isEqualTo(2);

        final Course refactoring = new Course("Refactoring", true);
        kent.addCourse(refactoring);
        kent.addCourse(new Course("Brutal Sarcasm", false));
        assertThat(kent.getCourses().size()).isEqualTo(4);

        kent.removeCourse(refactoring);
        assertThat(kent.getCourses().size()).isEqualTo(3);

        long advancedCourceCount = kent.numberOfAdvancedCourse();
        assertThat(advancedCourceCount).isEqualTo(1);
    }

    static class Employee {
        private int type;
        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;
        private int monthlySalary;
        private int commission;
        private int bonus;

        Employee(int type) {
            this.type = type;
        }

        int payAmount() {
            switch (type) {
                case ENGINEER:
                    return monthlySalary;
                case SALESMAN:
                    return monthlySalary + commission;
                case MANAGER:
                    return monthlySalary + bonus;
                default:
                    throw new RuntimeException("Incorrect Employee");
            }
        }
    }
}
