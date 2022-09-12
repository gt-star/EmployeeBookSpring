package com.example.employeebookspring.Service;

import com.example.employeebookspring.exception.EmployeeNotFoundException;
import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.exception.EmployeeAlreadyAddedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String ,Employee> employees = new HashMap<>();;
    private final ValidatorService validatorService;
    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }
    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }

    public String getKey(String name, String lastName){
        return name + " " + lastName;
    }
    public Employee addEmployees(String name, String lastName, double salary, int department){
        Employee employee = new Employee(validatorService.validateName(name),
                validatorService.validateLastName(lastName),
                salary,
                department);
        String key = getKey(employee.getName(),employee.getLastName());
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException();
        }
        return employees.put(key,employee);
    }
    public Employee removeEmployees(String name, String lastName){
        String key = getKey(name,lastName);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }
    public Employee findEmployees(String name, String lastName){
        String key = getKey(name,lastName);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }
}