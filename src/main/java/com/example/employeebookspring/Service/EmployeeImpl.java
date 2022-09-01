package com.example.employeebookspring.Service;

import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.exception.EmployeeAlreadyAddedException;
import com.example.employeebookspring.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeImpl implements EmployeeService {
    private static final int SIZE = 5;
    private final Map<String, Employee> employees;

    public EmployeeImpl() {
       employees = new HashMap<>();
    }

    @Override
    public Collection<Employee> getAll(){
        return Collections.unmodifiableCollection(employees.values());
    }
    @Override
    public Employee addEmployees(String name, String lastName){
        Employee employee = new Employee(name,lastName);
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException("");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }
    @Override
    public Employee removeEmployees(String name, String lastName){
        Employee employee = new Employee(name,lastName);
        if (employees.containsKey(employee.getFullName())){
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Нет такого сотрудника");
    }
    @Override
    public Employee findEmployees(String name, String lastName){
        Employee employee = new Employee(name,lastName);
        if (employees.containsKey(employee.getFullName())){
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Нет такого сотрудника");
    }
}
