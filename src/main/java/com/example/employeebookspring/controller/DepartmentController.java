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

    @GetMapping(path ="/max-salary")
    public Employee maxSalary(@RequestParam("departmentId")int departmentId){
        return departmentService.getMaxSalary(departmentId);
    }
    @GetMapping(path ="/min-salary")
    public Employee minSalary(@RequestParam("departmentId")int departmentId){
        return departmentService.getMinSalary(departmentId);
    }
    @GetMapping(path ="/allDepartmentId")
    public Map<Integer, List<Employee>> allDepartmentsId(@RequestParam("departmentId")int departmentId){
        return departmentService.getAllDepartmentId(departmentId);
    }
    @GetMapping(path ="/allDepartment")
    public Map<Integer, List<Employee>> allDepartmentsId(){
        return departmentService.getAllDepartment();
    }
}
