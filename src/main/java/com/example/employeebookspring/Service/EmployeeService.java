package com.example.employeebookspring.Service;
import com.example.employeebookspring.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getAll();
    Employee addEmployees(String name, String lastName, double salary, int department);

    Employee findEmployees(String name, String lastName);
    Employee removeEmployees(String name, String lastName);
    Employee getMaxSalary(int departmentId);

    Employee getMinSalary(int department);

    Map<Integer, List<Employee>> getAllDepartmentId(int departmentId);

    Map<Integer, List<Employee>> getAllDepartment();

}