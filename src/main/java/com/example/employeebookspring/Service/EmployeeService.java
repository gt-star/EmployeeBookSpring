package com.example.employeebookspring.Service;
import com.example.employeebookspring.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee addEmployees(String name, String lastName, double salary, int department);

    Employee findEmployees(String name, String lastName);
    Employee removeEmployees(String name, String lastName);

}