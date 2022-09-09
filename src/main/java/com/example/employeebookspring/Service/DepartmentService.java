package com.example.employeebookspring.Service;
import com.example.employeebookspring.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService{
    Employee getMaxSalary(int departmentId);

    Employee getMinSalary(int department);

    Map<Integer, List<Employee>> getAllDepartmentId(int departmentId);

    Map<Integer, List<Employee>> getAllDepartment();
}
