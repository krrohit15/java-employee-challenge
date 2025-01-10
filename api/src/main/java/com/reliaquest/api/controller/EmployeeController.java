package com.reliaquest.api.controller;

import com.reliaquest.api.model.Employee;
import com.reliaquest.api.model.EmployeeInput;
import com.reliaquest.api.service.EmployeeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmployeeController implements IEmployeeController<Employee, EmployeeInput> {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        System.out.println("inside fetch");
        log.info("** get all Employee ");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        log.info("** get all Employee with name search " + searchString);
        List<Employee> employees = employeeService.getEmployeesByNameSearch(searchString);
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        log.info("** get Employee with id " + id);
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        log.info("** get Employee highest salary ");
        int highestSalary = employeeService.getHighestSalaryOfEmployees();
        return ResponseEntity.ok(highestSalary);
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        log.info("** get all Employee with highest earning ");
        List<String> employeeNames = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseEntity.ok(employeeNames);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeInput employeeInput) {
        System.out.println("inside create");
        log.info("** create employee ");
        Employee employee = employeeService.createEmployee(employeeInput);

        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        log.info("** delete employee by id  " + id);
        String employeeName = employeeService.deleteEmployeeById(id);
        if (employeeName != null) {
            log.info("** delete employee by id  " + id + " successfully");
            return ResponseEntity.ok("Employee deleted successfully");
        } else {
            log.info("** delete employee by id  " + id + " not found ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " not found.");
        }
    }
}
