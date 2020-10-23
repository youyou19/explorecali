package com.itx.corps.playground.service;


import com.itx.corps.playground.aop.Loggable;
import com.itx.corps.playground.domain.Department;
import com.itx.corps.playground.domain.Employee;
import com.itx.corps.playground.repository.DepartmentRepository;
import com.itx.corps.playground.repository.EmployeePaginationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EmployeePaginationServiceImpl implements EmployeePaginationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeePaginationServiceImpl.class);

    private EmployeePaginationRepository employeePaginationRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeePaginationServiceImpl(EmployeePaginationRepository employeePaginationRepository, DepartmentRepository departmentRepository) {
        this.employeePaginationRepository = employeePaginationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Loggable
    @Override
    public Employee findBySalary(Double salary) {
        return employeePaginationRepository.findBySalary(salary);
    }
    @Loggable
    @Override
    public Employee findByName(String name) {
        return employeePaginationRepository.findByName(name).orElseThrow(()->
                new NoSuchElementException("Employee not found!!!!!!!!!! " + name)
        );
    }

    @Loggable
    @Override
    public List<Employee> getAll() {
        return employeePaginationRepository.findAll();
    }

    @Loggable
    @Override
    public Employee findSalaryAndName(String name, Double salary) {
        LOGGER.info("List Employee fo name {} and salary {}", name, salary);

        return employeePaginationRepository.findBySalaryIdAndName(name, salary);
    }
    @Loggable
    @Override
    public void addNewEmployee(String name, Double salary, String department) {
        LOGGER.info("Create Employee include {}, salary {} and department {}", name, salary, department);

        Department tour = departmentRepository.findOne(department);

        if(tour != null){
                employeePaginationRepository.save(new Employee(name,salary, tour));
            }else {
                new NoSuchElementException("No Department found!!!!!!!!!!!!");
            }
        }

    @Loggable
    public Page<Employee> lookupEmployees(String name, Pageable pageable) throws NoSuchElementException  {
        LOGGER.info("Lookup Rating for tour {}", name);
        return employeePaginationRepository.findByName(verifyEmployee(name).getName(), pageable);
    }

    @Loggable
    public Employee verifyEmployee(String name) throws NoSuchElementException {
        return employeePaginationRepository.findByName(name).orElseThrow(() ->
                new NoSuchElementException("Tour does not exist " + name)
        );
    }

}
