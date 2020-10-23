package com.itx.corps.playground.service;

import com.itx.corps.playground.domain.Department;
import com.itx.corps.playground.domain.Employee;
import com.itx.corps.playground.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceIntegrationTest {
    private static final String NAME = "George Washington";
    private static final double SALARY = 6700;
    private static final String DEPARTMENT = "Computer Science";

    @Autowired
    private EmployeePaginationService employeePaginationService;
    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    public void createNew(){
        //Verify New Department created.
        Department newDept = departmentRepository.findOne(DEPARTMENT);
        Employee newEmployee = employeePaginationService.findByName(NAME);
        assertThat(newDept.getName(), is(DEPARTMENT));
        assertThat(newEmployee.getName(), is(NAME));
        assertThat(newEmployee.getSalary(), is(SALARY));
        assertThat(newEmployee.getDepartment(), is(newDept));
    }
    //UnHappy Employee, Department does not exist
    @Test(expected = NoSuchElementException.class)
    public void createdNewException(){
        employeePaginationService.addNewEmployee(NAME, SALARY, DEPARTMENT);
    }

    //Happy Path many Employee Rate one page
    @Test
    public void rateMany(){
        int counts = employeePaginationService.getAll().size();
        employeePaginationService.addNewEmployee(NAME,SALARY, DEPARTMENT);
        assertThat(employeePaginationService.getAll(), is(counts + 3));
    }

    //Unhappy Path, 2nd Invocation would create duplicates in the database, DataIntegrityViolationException thrown
    @Test(expected = DataIntegrityViolationException.class)
    public void rateManyProveRoolback(){
        int counts = employeePaginationService.getAll().size();
        employeePaginationService.addNewEmployee(NAME,SALARY, DEPARTMENT);
        employeePaginationService.addNewEmployee("Medor Rodrigue", 23456.00, "Branch");
        employeePaginationService.addNewEmployee("Medor Sara", 234656.00, "Branch");
    }


}
