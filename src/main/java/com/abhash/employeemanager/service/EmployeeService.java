package com.abhash.employeemanager.service;

import com.abhash.employeemanager.exception.UserNotFoundException;
import com.abhash.employeemanager.model.Employee;
import com.abhash.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return  employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id "+id+" not found"));
    }
}
