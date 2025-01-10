package com.reliaquest.api.service;

import com.reliaquest.api.model.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private Map<UUID, Employee> employeeMap = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    public List<Employee> getEmployeesByNameSearch(String nameSearch) {
        return employeeMap.values().stream()
                .filter(e -> e.getName().toLowerCase().contains(nameSearch.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Employee getEmployeeById(String id) {
        return employeeMap.get(id);
    }

    public int getHighestSalaryOfEmployees() {
        return employeeMap.values().stream().mapToInt(Employee::getSalary).max().orElse(0);
    }

    public List<String> getTopTenHighestEarningEmployeeNames() {
        return employeeMap.values().stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    public Employee createEmployee(EmployeeInput employeeInput) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setName(employeeInput.getName());
        employee.setSalary(employeeInput.getSalary());
        employee.setTitle(employeeInput.getTitle());
        if (employeeInput.getAge() >= 16 && employeeInput.getAge() <= 75) {
            employee.setAge(employeeInput.getAge());
        }

        employee.setEmail(employeeInput.getEmail());
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public String deleteEmployeeById(String id) {
    	 System.out.println("inside delete "+ employeeMap.toString());
        if (employeeMap.containsKey(id)) {
            Employee employee = employeeMap.remove(id);
            return employee.getName();
        } else {
            return "";
        }
    }
}
