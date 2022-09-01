package com.example.employeebookspring.Service;

import com.example.employeebookspring.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Collection<Employee> getAll();

    Employee addEmployees(String name, String lastName);

    Employee removeEmployees(String name, String lastName);

    Employee findEmployees(String name, String lastName);
}
