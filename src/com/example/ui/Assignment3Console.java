package com.example.ui;

import com.example.controller.EmployeeService;
import com.example.controller.LoginService;
import com.example.entity.Account;
import com.example.entity.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment3Console {
    LoginService loginService = new LoginService();
    EmployeeService employeeService = new EmployeeService();
    Account account = new Account();
    private final Scanner sc;

    public Assignment3Console() throws IOException, SQLException {
        this.sc = new Scanner(System.in);
    }

    private int menu() {
        System.out.println("---EMPLOYEE MANAGEMENT---");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Remove Employee");
        System.out.println("4. Search Employee");
        System.out.println("5. Get All Employee");
        System.out.println("0. Exit");
        int choice = readInt(0, 5);
        return choice;
    }

    private int searchMenu() {
        System.out.println("---SEARCH EMPLOYEE---");
        System.out.println("1. Search Employee By Id");
        System.out.println("2. Search Employee By First Name");
        System.out.println("3. Back");
        int choice = readInt(1, 3);
        return choice;
    }

    public void preStart() throws SQLException {
        while (true) {
            System.out.println("Admin Login !!!");
            boolean result = loginPreparedStatement();
            if (result) {
                break;
            } else {
                System.out.println("Login fail, Please login again !!!");
            }
        }
    }

    public void start() throws SQLException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    int choice1 = searchMenu();
                    switch (choice1) {
                        case 1:
                            getEmployeeById();
                            break;
                        case 2:
                            getEmployeesByFirstName();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 5:
                    showAll();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        return choice;
    }

    private double readDouble(int min, double max) {
        double price;
        while (true) {
            try {
                price = Integer.parseInt(sc.nextLine());
                if (price >= min && price <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        return price;
    }

    private boolean loginPreparedStatement() throws SQLException {
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        account.setUsername(username);
        account.setPassword(password);
        return loginService.loginPreparedStatementController(account);
    }

    private void addEmployee() {
        System.out.println("Enter employee firstname: ");
        String firstname = sc.nextLine();
        System.out.println("Enter employee lastname");
        String lastname = sc.nextLine();
        System.out.println("Enter employee salary");
        double salary = readDouble(0, Double.MAX_VALUE);
        Employee employee = new Employee(firstname, lastname, salary);
        employeeService.createEmployee(employee);
    }

    private void showAll() {
        System.out.println("--All product--");
        System.out.println("ID\tFistName\tLastName\tSalary");
        ArrayList<Employee> allEmployees = employeeService.getAllEmployee();

        allEmployees.forEach(employee -> {
            System.out.println(employee.getEmpID() + "\t" + employee.getFirstName() + "\t\t"
                    + employee.getLastName() + "\t\t" + employee.getSalary());
        });
    }

    private void removeEmployee() {
        System.out.println("Enter ID of employee");
        int empID = readInt(0, Integer.MAX_VALUE);
        employeeService.deleteEmployee(empID);
    }

    private void getEmployeeById() {
        System.out.println("Enter ID of employee");
        int empID = readInt(0, Integer.MAX_VALUE);
        System.out.println("ID\tFistName\tLastName\tSalary");
        Employee employee = employeeService.getEmployeeById(empID);
        System.out.println(employee.getEmpID() + "\t" + employee.getFirstName() + "\t"
                + employee.getLastName() + "\t" + employee.getSalary());
    }

    private void getEmployeesByFirstName() {
        System.out.println("Enter first name of employee: ");
        String firstName = sc.nextLine();
        System.out.println("ID\tFistName\tLastName\tSalary");
        ArrayList<Employee> allEmployees = employeeService.getEmployeesByFirstName(firstName);
        allEmployees.forEach(employee -> {
            System.out.println(employee.getEmpID() + "\t" + employee.getFirstName() + "\t\t"
                    + employee.getLastName() + "\t\t" + employee.getSalary());
        });
    }

    private void updateEmployee() {
        System.out.println("Enter employee id: ");
        int empID = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter employee firstname: ");
        String firstname = sc.nextLine();
        System.out.println("Enter employee lastname");
        String lastname = sc.nextLine();
        System.out.println("Enter product salary");
        double salary = readDouble(0, Double.MAX_VALUE);
        Employee employee = new Employee(empID, firstname, lastname, salary);
        employeeService.updateEmployee(employee);
    }
}
