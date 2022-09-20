package com.example.employeebookspring.controller;

import com.example.employeebookspring.model.Employee;
import com.example.employeebookspring.Service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
@RestController
@RequestMapping("/employee")
public class EmployeeController extends ResponseEntityExceptionHandler {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/add")
    @ResponseBody
    public Employee addEmployees(@RequestParam("name") String name,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("salary")double salary,
                                 @RequestParam("department")int department){
        if (StringUtils.isNoneEmpty(name,lastName)){
            return employeeService.addEmployees(name,lastName,salary, department);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Поле не должно быть пустым");
        }
    }

    @GetMapping("/remove")
    public Employee removeEmployees(@RequestParam("name") String name,
                                    @RequestParam("lastName") String lastName){
        return employeeService.removeEmployees(name,lastName);
    }
    @GetMapping("/find")
    public Employee findEmployees(@RequestParam("name") String name,
                                  @RequestParam("lastName") String lastName) {
        return employeeService.findEmployees(name, lastName);
    }

}
