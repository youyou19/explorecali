package com.itx.corps.playground.service;


import com.itx.corps.playground.domain.Department;
import com.itx.corps.playground.domain.Employee;
import com.itx.corps.playground.repository.DepartmentRepository;
import com.itx.corps.playground.repository.EmployeePaginationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceMockTest {

    private static final String NAME = "George Washington";
    private static final Double SALARY = 6700d;
    private static final String DEPARTMENT = "Computer Science";

    @Mock
    private EmployeePaginationRepository employeePaginationRepositoryMock;
    @Mock
    private DepartmentRepository departmentRepositoryMock;
    @InjectMocks //Autowire TourRatingService(employeePaginationRepositoryMock, departmentRepositoryMock)
    private EmployeePaginationService service;
    @Mock
    private Employee employeeMock;
    @Mock
    private Department departmentMock;
    /**
     * Mock responses to commonly invoked methods.
     */
    @Before
    public void setupReturnValuesOfMockMethods(){
        when(departmentRepositoryMock.findOne(DEPARTMENT)).thenReturn(departmentMock);
        when(employeeMock.getName()).thenReturn(NAME);
        when(employeeMock.getSalary()).thenReturn(SALARY);
        when(employeeMock.getDepartment().getName()).thenReturn(DEPARTMENT);
    }
    /**************************************************************************************
     *
     * Verify the service return value
     *
     **************************************************************************************/
    @Test
    public void lookupEmployeeByName() {
        when(employeePaginationRepositoryMock.findOne(NAME)).thenReturn(employeeMock);
        assertThat(service.findByName(NAME),is(employeeMock));
        assertThat(service.findBySalary(SALARY),is(employeeMock));
        assertThat(service.findSalaryAndName(NAME, SALARY),is(employeeMock));
    }

    @Test
    public void lookupAll() {
        when(employeePaginationRepositoryMock.findAll()).thenReturn(Arrays.asList(employeeMock));

        //invoke and verify lookupAll
        assertThat(service.getAll().get(0), is(employeeMock));
    }



    @Test
    public void lookupRatings() {
        //create mocks of Pageable and Page (only needed in this test)
        Pageable pageable = mock(Pageable.class);
        Page page = mock(Page.class);
        when(employeePaginationRepositoryMock.findByName(NAME, pageable)).thenReturn(page);
    }




    /**************************************************************************************
     *
     * Verify the invocation of dependencies
     * Capture parameter values.
     * Verify the parameters.
     *
     **************************************************************************************/

    @Test
    public void createNew() {
        //prepare to capture a Employee Object
        ArgumentCaptor<Employee> tourRatingCaptor = ArgumentCaptor.forClass(Employee.class);

        //invoke createNew

        service.addNewEmployee(NAME, SALARY, DEPARTMENT);

        //verify employeePaginationRepository.save invoked once and capture the Employee Object
        verify(employeePaginationRepositoryMock).save(tourRatingCaptor.capture());

        //verify the attributes of the Tour Rating Object
        assertThat(tourRatingCaptor.getValue().getName(), is(employeeMock));
        assertThat(tourRatingCaptor.getValue().getSalary(), is(SALARY));
        assertThat(tourRatingCaptor.getValue().getDepartment(), is(DEPARTMENT));

    }
}
