package com.itx.corps.playground.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class EmployeeTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        Department department = new Department("Computer Science");
        Employee p = new Employee("George Washington",2000d,department);
        //assertNull(p.getName());
        assertThat(p.getName(), is("George Washington"));
        assertThat(p.getSalary(), is(456));
        assertThat(p.getDepartment(), is("Computer Science"));

    }

    @Test
    public void equalsHashcodeVerify() {
        Department department = new Department("name");
        Employee p = new Employee("name",2000d,department);
        Employee p1 = new Employee("name",2000d,department);
        assertThat(p, is(p1));
        assertThat(p.hashCode(), is(p1.hashCode()));
    }

}
