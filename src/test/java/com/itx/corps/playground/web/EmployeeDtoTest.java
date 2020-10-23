package com.itx.corps.playground.web;

import com.itx.corps.playground.dto.EmployeeDto;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class EmployeeDtoTest {
    @Test
    public void testConstructor() throws Exception {
        EmployeeDto dto = new EmployeeDto("Rodrige Medor",3356d, "Computer Science");
        assertThat(dto.getName(), is("Rodrige Medor"));
        assertThat(dto.getSalary(), is(3356d));
        assertThat(dto.getDepartment(), is("Computer Science"));
    }

    @Test
    public void testSetters() {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("Rodrige Medor");
        dto.setSalary(3356d);
        dto.setDepartment("Computer Science");
        assertThat(dto.getName(), is(1));
        assertThat(dto.getSalary(), is(3356d));
        assertThat(dto.getDepartment(), is("Computer Science"));
    }

}
