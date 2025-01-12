package com.reliaquest.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.reliaquest.api.controller.EmployeeController;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.request.EmployeeCreateRequest;
import com.reliaquest.api.service.EmployeeService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ApiApplicationTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = List.of(new Employee(), new Employee());
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(ResponseEntity.ok(employees), response);
        verify(employeeService, times(1)).getAllEmployees();
    }

    /*
        @Test
        public void testGetEmployeesByNameSearch() {
            String searchString = "John";
            List<Employee> employees = List.of(new Employee(), new Employee());
            when(employeeService.getEmployeesByNameSearch(searchString)).thenReturn(employees);

            ResponseEntity<List<Employee>> response = employeeController.getEmployeesByNameSearch(searchString);

            assertEquals(ResponseEntity.ok(employees), response);
            verify(employeeService, times(1)).getEmployeesByNameSearch(searchString);
        }

        @Test
        public void testGetEmployeeById() {
            String id = "1";
            Employee employee = new Employee();
            when(employeeService.getEmployeeById(id)).thenReturn(employee);

            ResponseEntity<Employee> response = employeeController.getEmployeeById(id);

            assertEquals(ResponseEntity.ok(employee), response);
            verify(employeeService, times(1)).getEmployeeById(id);
        }

        @Test
        public void testGetHighestSalaryOfEmployees() {
            int highestSalary = 100000;
            when(employeeService.getHighestSalaryOfEmployees()).thenReturn(highestSalary);

            ResponseEntity<Integer> response = employeeController.getHighestSalaryOfEmployees();

            assertEquals(ResponseEntity.ok(highestSalary), response);
            verify(employeeService, times(1)).getHighestSalaryOfEmployees();
        }

        @Test
        public void testGetTopTenHighestEarningEmployeeNames() {
            List<String> employeeNames = List.of("John", "Jane");
            when(employeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(employeeNames);

            ResponseEntity<List<String>> response = employeeController.getTopTenHighestEarningEmployeeNames();

            assertEquals(ResponseEntity.ok(employeeNames), response);
            verify(employeeService, times(1)).getTopTenHighestEarningEmployeeNames();
        }
    */
    @Test
    public void testCreateEmployee() {
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        Employee employee = new Employee();
        when(employeeService.createEmployee(request)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.createEmployee(request);

        assertEquals(ResponseEntity.ok(employee), response);
        verify(employeeService, times(1)).createEmployee(request);
    }

    /*
    @Test
    public void testDeleteEmployeeById() {
        String id = "1";
        String name = "John";
        when(employeeService.deleteEmployeeById(id)).thenReturn(name);

        ResponseEntity<String> response = employeeController.deleteEmployeeById(id);

        assertEquals(ResponseEntity.ok(name), response);
        verify(employeeService, times(1)).deleteEmployeeById(id);
    }
    */
}
