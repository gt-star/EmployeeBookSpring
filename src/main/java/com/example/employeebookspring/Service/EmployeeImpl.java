package com.example.employeebookspring.Service;

import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.exception.EmployeeAlreadyAddedException;
import com.example.employeebookspring.exception.EmployeeNotFoundException;
import com.example.employeebookspring.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class EmployeeImpl implements EmployeeService {
    private static final int SIZE = 5;
    private final List<Employee> employees;

    public EmployeeImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public List<Employee> getAll(){
        return Collections.unmodifiableList(employees);
    }

    @Override
    public Employee addEmployees(String name, String lastName){
        Employee employee = new Employee(name,lastName);
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        if (employees.size()<SIZE){
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
    }
    @Override
    public Employee removeEmployees(String name, String lastName){
        Employee employee = new Employee(name,lastName);
        if (employees.contains(employee)){
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Нет такого сотрудника");
    }
    @Override
    public Employee findEmployees(String name, String lastName){
        Employee employee = new Employee(name,lastName);
        if (employees.contains(employee)){
            return employee;
        }
        throw new EmployeeNotFoundException("Нет такого сотрудника");
    }
}
