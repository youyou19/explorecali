package com.itx.corps.playground.web;


import com.itx.corps.playground.domain.Department;
import com.itx.corps.playground.domain.Employee;
import com.itx.corps.playground.dto.EmployeeDto;
import com.itx.corps.playground.service.EmployeePaginationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Invoke the Controller methods via HTTP.
 * Do not invoke the employeeService methods, use Mock instead
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class EmployeeWebControllerTest {
    private static final String EMPLOYEES_URL = "/employee";

    //These Employee and department do not already exist in the db
    private static final String NAME = "George Washington";
    private static final Double SALARY = 6700d;
    private static final String DEPARTMENT = "Computer Science";

    @MockBean
    private EmployeePaginationService employeePaginationServiceMock;

    @Mock
    private Employee employeegMock;

    @Mock
    private Department departmentMock;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setupReturnValuesOfMockMethods() {
        when(employeegMock.getDepartment()).thenReturn(departmentMock);
        when(employeegMock.getName()).thenReturn(NAME);
        when(employeegMock.getSalary()).thenReturn(SALARY);
    }


    /**
     *  HTTP GET /employee
     */
    @Test
    public void getEmployee() {
        when(employeePaginationServiceMock.getAll()).thenReturn(Arrays.asList(employeegMock));

        ResponseEntity<List<EmployeeDto>> response = restTemplate.exchange(EMPLOYEES_URL, HttpMethod.GET,null,
                                                    new ParameterizedTypeReference<List<EmployeeDto>>() {});

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(4));
    }

    /**
     *  HTTP GET /employee/{name}
     */
    @Test
    public void getOne()  {

        when(employeePaginationServiceMock.getAll()).thenReturn(Arrays.asList(employeegMock));

        ResponseEntity<EmployeeDto> response =
                restTemplate.getForEntity(EMPLOYEES_URL + "/" + NAME, EmployeeDto.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getName(), is(NAME));
        assertThat(response.getBody().getSalary(), is(SALARY));
        assertThat(response.getBody().getDepartment(), is(DEPARTMENT));
    }
}
