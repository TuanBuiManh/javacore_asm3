package com.example.entity;

public class Employee {
    private int empID;
    private String FirstName;
    private String LastName;
    private double Salary;

    public Employee() {
    }

    public Employee(int empID, String firstName, String lastName, double salary) {
        this.empID = empID;
        FirstName = firstName;
        LastName = lastName;
        Salary = salary;
    }

    public Employee(String firstName, String lastName, double salary) {
        FirstName = firstName;
        LastName = lastName;
        Salary = salary;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
