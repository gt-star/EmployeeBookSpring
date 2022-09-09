package com.example.employeebookspring.Service;

import com.example.employeebookspring.exception.EmployeeNotFoundException;
import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.exception.EmployeeAlreadyAddedException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class EmployeeImpl implements EmployeeService,DepartmentService{
    private final Map<String ,Employee> employees;

    public EmployeeImpl() {
        this.employees = new HashMap<>();
    }
    @Override
    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }

    public String getKey(String name, String lastName){
        return name + " " + lastName;
    }
    @Override
    public Employee addEmployees(String name, String lastName, double salary, int department){
        Employee employee = new Employee(name,lastName,salary, department);
        String key = getKey(name,lastName);
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        return  employees.put(key ,employee);
    }
    @Override
    public Employee removeEmployees(String name, String lastName){
        String key = getKey(name,lastName);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
        return employees.remove(key);
    }
    @Override
    public Employee findEmployees(String name, String lastName){
        String key = getKey(name,lastName);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
        return employees.get(key);
    }

    @Override
    public Employee getMaxSalary(int department){
        List<Employee> list = new ArrayList<>(employees.values());
        return list.stream().filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    @Override
    public Employee getMinSalary(int department) {
        List<Employee> list = new ArrayList<>(employees.values());
        return list.stream().filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    @Override
    public Map<Integer, List<Employee>> getAllDepartmentId(int departmentId) {
        List<Employee> list = new ArrayList<>(employees.values());
        return list.stream().filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Map<Integer, List<Employee>> getAllDepartment() {
        List<Employee> list = new ArrayList<>(employees.values());
        return list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}