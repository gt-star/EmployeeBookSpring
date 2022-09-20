package com.example.employeebookspring.ServiceTest;

import com.example.employeebookspring.Service.EmployeeService;
import com.example.employeebookspring.Service.ValidatorService;
import com.example.employeebookspring.model.Employee;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService(new ValidatorService());
    Employee employee;
    Map<String, Employee> employeeMap = new HashMap<>();

    @ParameterizedTest
    @MethodSource("params")
    void checkingAddingAnEmployee(String name,String lastName, double salary, int department){
        employeeService.addEmployees(name,lastName,salary,department);
        employeeMap.put(employeeService.getKey(name,lastName), new Employee(name,lastName,salary,department));
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkingForEqualsEmployee(String name,String lastName, double salary, int department){
        employee = new Employee(name,lastName,salary,department);
        assertEquals(employee, new Employee(name,lastName,salary,department));
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkinForNotNullValue(String name,String lastName, double salary, int department){
        employee = new Employee(name,lastName,salary,department);
        assertNotNull(employee.getName());
        assertNotNull(employee.getLastName());
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkingForPrimitiveNegativeValue(String name,String lastName, double salary, int department){
        Stream<Double> streamSalary = Stream.of(salary);
        boolean resultSalary = streamSalary.allMatch(x -> x > 0);
        assertTrue(resultSalary);

        Stream<Integer> streamDepartment = Stream.of(department);
        boolean resultDepartment = streamDepartment.allMatch(x -> x > 0);
        assertTrue(resultDepartment);
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkingForFindEmployee(String name,String lastName, double salary, int department){
        String key = employeeService.getKey(name,lastName);
        employeeMap.put(key,new Employee(name,lastName,salary,department));
        boolean result = employeeMap.containsKey(key);
        assertTrue(result);
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkingForNotFindEmployee(String name,String lastName, double salary, int department){
        String key = employeeService.getKey(name,lastName);
        employeeMap.put(key,new Employee(name,lastName,salary,department));
        boolean result = employeeMap.containsKey(employeeService.getKey("AS","DS"));
        assertFalse(result);
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkingForRemoveEmployee(String name,String lastName, double salary, int department){
        String key = employeeService.getKey(name,lastName);
        employeeMap.put(key,new Employee(name,lastName,salary,department));
        assertTrue(employeeMap.containsKey(key));
    }
    @ParameterizedTest
    @MethodSource("params")
    void checkingForNotRemoveEmployee(String name,String lastName, double salary, int department){
        String key = employeeService.getKey(name,lastName);
        employeeMap.put(key,new Employee(name,lastName,salary,department));
        assertFalse(employeeMap.containsKey(employeeService.getKey("AS","DS")));
    }
    @ParameterizedTest
    static Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("ivan", "ivanov", 32142, 1),
                Arguments.of("jon", "conor", 43221,1),
                Arguments.of("sara", "conor", 505341,2)
        );
    }
}