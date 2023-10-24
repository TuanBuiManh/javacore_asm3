package com.example.model;

import com.example.entity.Employee;

import java.util.ArrayList;

public interface EmployeeDao {

    public void createEmployee(Employee employee);

    public Employee getEmployeeById(int empID);

    public ArrayList<Employee> getEmployeesByFirstName(String firstName);

    public ArrayList<Employee> getAllEmployee();

    public void deleteEmployee(int empID);

    public void updateEmployee(Employee employee);

}
