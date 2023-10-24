package com.example.model;

import com.example.dao.DBConnection;
import com.example.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDaoImpl implements EmployeeDao{
    private final Connection conn = DBConnection.createConnection();
    private final String SQL_CREATE_EMPLOYEE = "INSERT INTO employee (FirstName, LastName, Salary) VALUES (?, ?, ?)";
    private final String SQL_GET_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE empID = ?";
    private final String SQL_GET_EMPLOYEES_BY_FIRST_NAME = "SELECT * FROM employee WHERE FirstName like ?";
    private final String SQL_GET_ALL_EMPLOYEES = "SELECT * FROM employee";
    private final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE empID = ?";
    private final String SQL_UPDATE_EMPLOYEE = "UPDATE employee SET FirstName = ?, LastName = ?, Salary = ? WHERE empID = ?";

    public EmployeeDaoImpl() throws SQLException {
    }

    @Override
    public void createEmployee(Employee employee) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_EMPLOYEE,
                Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, employee.getFirstName());
            pstm.setString(2, employee.getLastName());
            pstm.setDouble(3, employee.getSalary());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setEmpID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Employee getEmployeeById(int empID) {
        Employee employee = new Employee();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_EMPLOYEE_BY_ID)) {
            pstm.setInt(1, empID);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    employee.setEmpID(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setSalary(rs.getDouble(4));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    @Override
    public ArrayList<Employee> getEmployeesByFirstName(String firstName) {
        ArrayList<Employee> allEmployees = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_EMPLOYEES_BY_FIRST_NAME)) {
            pstm.setString(1, "%" + firstName + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmpID(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setSalary(rs.getDouble(4));
                    allEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEmployees;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> allEmployees = new ArrayList();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_EMPLOYEES);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpID(rs.getInt(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setSalary(rs.getDouble(4));
                allEmployees.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEmployees;
    }

    @Override
    public void deleteEmployee(int empID) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_EMPLOYEE)) {
            pstm.setInt(1, empID);
            int ud = pstm.executeUpdate();
            if (ud > 0) {
                System.out.println("Update Successful");
            } else {
                System.out.println("Update Fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_EMPLOYEE)) {
            pstm.setString(1, employee.getFirstName());
            pstm.setString(2, employee.getLastName());
            pstm.setDouble(3, employee.getSalary());
            pstm.setInt(4, employee.getEmpID());
            int ud = pstm.executeUpdate();
            if (ud > 0) {
                System.out.println("Update Successful");
            } else {
                System.out.println("Update Fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
