package com.example.employeebookspring.ServiceTest;

import com.example.employeebookspring.Service.DepartmentService;
import com.example.employeebookspring.Service.EmployeeService;
import com.example.employeebookspring.exception.EmployeeNotFoundException;
import com.example.employeebookspring.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    public EmployeeService employeeService;
    @InjectMocks
    public DepartmentService departmentService;

    @BeforeEach
    void beforeEch(){
        List<Employee> employees = List.of(
                new Employee("Иван","Петров",40000,1),
                new Employee("Петров","Иванов",24000,2),
                new Employee("Иван","Иванов",40000,1),
                new Employee("Петр","Петров",24000,3),
                new Employee("Мария","Петровна",24000,1)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }
    @Test
    void checkingGetDepartmentId(){
        assertThat(departmentService.getAllDepartmentId(4)).isEmpty();
    }
    @Test
    void checkingNotEmployees(){
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        assertThat(departmentService.getAllDepartment()).isEmpty();
        assertThat(departmentService.getAllDepartmentId(1)).isEmpty();
    }
    @ParameterizedTest
    @MethodSource("employeeParamsMaxSalary")
    void checkingForMaxSalary(int departmentId, Employee expected){
        assertThat(departmentService.getMaxSalary(departmentId)).isEqualTo(expected);
    }
    @Test
    void checkingForMaxSalaryNegative(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.getMaxSalary(1));
    }

    @ParameterizedTest
    @MethodSource("employeeParamsMinSalary")
    void checkingForMinSalary(int departmentId, Employee expected){
        assertThat(departmentService.getMinSalary(departmentId)).isEqualTo(expected);
    }
    @Test
    void checkingForMinSalaryNegative(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.getMinSalary(1));
    }
    @ParameterizedTest
    @MethodSource("employeesDepartmentParams")
    void employeesFromDepartmentPositive(int departmentId, List<Employee> expected){
        assertThat(departmentService.getAllDepartmentId(departmentId)).containsExactlyElementsOf(expected);
    }
    @Test
    void employeesDepartmentTest(){
        assertThat(departmentService.getAllDepartment()).containsAllEntriesOf(
                Map.of(1,List.of(new Employee("Иван","Петров",40000,1),
                                new Employee("Петров","Иванов",24000,2)),
                        2,List.of(new Employee("Иван","Иванов",45000,1),
                                new Employee("Петр","Петров",26000,2),
                                new Employee("Мария","Петровна",28000,1))
                )
        );
    }
    static Stream<Arguments> employeeParamsMaxSalary(){
        return Stream.of(
                Arguments.of(1,new Employee("Иван","Петров",40000,1)),
                Arguments.of(2, new Employee("Иван","Иванов",45000,1))
        );
    }
    static Stream<Arguments> employeeParamsMinSalary(){
        return Stream.of(
                Arguments.of(1,new Employee("Мария","Петровна",28000,1)),
                Arguments.of(2, new Employee("Петр","Петров",26000,2))
        );
    }
    static Stream<Arguments> employeesDepartmentParams(){
        return Stream.of(
                Arguments.of(1,List.of(new Employee("Иван","Петров",40000,1),
                        new Employee("Петров","Иванов",24000,2))),
                Arguments.of(1,List.of(new Employee("Иван","Иванов",45000,1),
                        new Employee("Петр","Петров",26000,2),
                        new Employee("Мария","Петровна",28000,1))),
                Arguments.of(3,Collections.emptyList())
        );
    }
}
