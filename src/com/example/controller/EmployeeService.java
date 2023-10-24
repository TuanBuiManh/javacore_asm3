package com.example.controller;

import com.example.entity.Employee;
import com.example.model.EmployeeDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public EmployeeService() throws SQLException {
    }

    public void createEmployee(Employee employee) {
        employeeDao.createEmployee(employee);
    }

    public Employee getEmployeeById(int empID) {
        return employeeDao.getEmployeeById(empID);
    }

    public ArrayList<Employee> getEmployeesByFirstName(String firstName) {
        return employeeDao.getEmployeesByFirstName(firstName);
    }

    public ArrayList<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    public void deleteEmployee(int empID) {
        employeeDao.deleteEmployee(empID);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }
}
