package com.itx.corps.playground.service;



import com.itx.corps.playground.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;

public interface EmployeePaginationService {


    Employee findBySalary(Double salary);

    Employee findByName(String name);

    public void addNewEmployee(String name, Double salary, String department);

    public List<Employee> getAll();

    public Employee findSalaryAndName(String name, Double salary);
    public Page<Employee> lookupEmployees(String name, Pageable pageable) throws NoSuchElementException;
}
