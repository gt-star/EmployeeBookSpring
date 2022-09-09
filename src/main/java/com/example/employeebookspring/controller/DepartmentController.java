//package com.example.employeebookspring.controller;
//
//import com.example.employeebookspring.model.Employee;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/departments")
//public class DepartmentController{
//    private final DepartmentController departmentController;
//
//    public DepartmentController(DepartmentController departmentController) {
//        this.departmentController = departmentController;
//    }
//    @GetMapping(path ="/max-salary")
//    public Employee maxSalary(@RequestParam("departmentId")int departmentId){
//        return departmentController.getMaxSalary(departmentId);
//    }
//    @GetMapping(path ="/min-salary")
//    public Employee minSalary(@RequestParam("departmentId")int departmentId){
//        return departmentController.getMinSalary(departmentId);
//    }
//    @GetMapping(path ="/allDepartmentId")
//    public Map<Integer, List<Employee>> allDepartmentsId(@RequestParam("departmentId")int departmentId){
//        return departmentController.getAllDepartmentId(departmentId);
//    }
//    @GetMapping(path ="/all")
//    public Map<Integer, List<Employee>> allDepartmentsId(){
//        return departmentController.getAllDepartment();
//    }
//}
