package com.example.employeebookspring.controller;

import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
                               @RequestParam("lastName") String lastName){
        return employeeService.addEmployees(name,lastName);
    }
    @GetMapping(path = "/remove")
    public Employee removeEmployees(@RequestParam("name") String name,
                                  @RequestParam("lastName") String lastName){
        return employeeService.removeEmployees(name,lastName);
    }
    @GetMapping(path = "/find")
    public Employee findEmployees(@RequestParam("name") String name,
                                  @RequestParam("lastName") String lastName){
        return employeeService.findEmployees(name,lastName);
    }
}
