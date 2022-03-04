package com.company;

public class Employee {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private float salary;
    private int department;
    private final int id;
    public static int staticid = 0;

    public Employee(String firstName, String middleName, String lastName, int salary, int department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;

        this.id = staticid++;
    }

    public float getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public int getDepartment() {
        return department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

}
