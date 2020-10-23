package com.itx.corps.playground.web;


import com.itx.corps.playground.aop.Loggable;
import com.itx.corps.playground.domain.Employee;
import com.itx.corps.playground.dto.EmployeeDto;
import com.itx.corps.playground.service.EmployeePaginationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Api(description = "API to just pull Employee")
@RestController
@RequestMapping(path ="/employee")
public class EmployeePaginationController {

    private EmployeePaginationService employeePaginationService;

    @Autowired
    public EmployeePaginationController(EmployeePaginationService employeePaginationService) {
        this.employeePaginationService = employeePaginationService;
    }

    /*helper method for Employee using DTO Pattern*/
    private EmployeeDto toDto(Employee employee){
        return new EmployeeDto(employee.getName(), employee.getSalary(), employee.getDepartment().getName());
    }

    @Loggable
    @ApiOperation(value = "Find all Employee")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
    @GetMapping
    public List<Employee> getAll(){
        return this.employeePaginationService.getAll();
    }

    /*Add new Employee*/
    @Loggable
    @ApiOperation(value = "Add an Employee")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveCustomer(@RequestBody @Validated EmployeeDto employeeDto){


                employeePaginationService.addNewEmployee(employeeDto.getName(), employeeDto.getSalary(), employeeDto.getDepartment());

    }
    /*Find an Employee by salary*/
    @Loggable
    @ApiOperation(value = "Find an Employee by salary")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
    @GetMapping("/suchSalary/{salary}")
    public Employee getBySalary(@PathVariable("salary") Double saraly){
        return   employeePaginationService.findBySalary(saraly);
    }
        /*Find an Employee by name*/
    @Loggable
    @ApiOperation(value = "Find an Employee by name")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
    @GetMapping(value = "/suchName/{name}")
    public Employee getByName(@PathVariable("name") String name){
        return   employeePaginationService.findByName(name);
    }

    /*Find an Employee by name and Salary*/
    @Loggable
    @ApiOperation(value = "Find an Employee by name and Salary")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
    @GetMapping(value = "/{name}/{salary}")
    public Employee getByNameAndSalary(@PathVariable("name") String name, @PathVariable(value = "salary") Double salary){
        return   employeePaginationService.findSalaryAndName(name,salary);
    }
}
