package com.example.employeebookspring.controller;

import com.example.employeebookspring.Service.DepartmentService;
import com.example.employeebookspring.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/departments")
public class DepartmentController{
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee getMaxSalary(@RequestParam("departmentId")int departmentId){
        return departmentService.getMaxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee getMinSalary(@RequestParam("departmentId")int departmentId){
        return departmentService.getMinSalary(departmentId);
    }
    @GetMapping(value ="/allDepartment",params = "departmentId")
    public List<Employee> getAllDepartmentId(@RequestParam("departmentId")int departmentId){
        return departmentService.getAllDepartmentId(departmentId);
    }
    @GetMapping("/allDepartment")
    public Map<Integer, List<Employee>> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
}
