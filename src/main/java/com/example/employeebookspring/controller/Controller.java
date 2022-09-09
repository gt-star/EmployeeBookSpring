package com.example.employeebookspring.controller;

import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class Controller {
    private final EmployeeService employeeService;
    public Controller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/get")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping(path = "/add")
    public Employee addEmployees(@RequestParam("name") String name,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("salary")double salary,
                                 @RequestParam("department")int department){
        return employeeService.addEmployees(name,lastName,salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployees(@RequestParam("name") String name,
                                    @RequestParam("lastName") String lastName){
        return employeeService.removeEmployees(name,lastName);
    }
    @GetMapping(path = "/find")
    public Employee findEmployees(@RequestParam("name") String name,
                                  @RequestParam("lastName") String lastName) {
        return employeeService.findEmployees(name, lastName);
    }
    @GetMapping(path ="/max-salary")
    public Employee maxSalary(@RequestParam("departmentId")int departmentId){
        return employeeService.getMaxSalary(departmentId);
    }
    @GetMapping(path ="/min-salary")
    public Employee minSalary(@RequestParam("departmentId")int departmentId){
        return employeeService.getMinSalary(departmentId);
    }
    @GetMapping(path ="/allDepartmentId")
    public Map<Integer, List<Employee>> allDepartmentsId(@RequestParam("departmentId")int departmentId){
        return employeeService.getAllDepartmentId(departmentId);
    }
    @GetMapping(path ="/all")
    public Map<Integer, List<Employee>> allDepartmentsId(){
        return employeeService.getAllDepartment();
    }

}
