package com.itx.corps.playground.repository;


import com.itx.corps.playground.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface EmployeePaginationRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByName(@Param("name") String name);

    @Query("select emp from Employee emp where emp.salary =:salary")
    Employee findBySalary(@Param("salary") Double salary);


    @Query("select emp from Employee emp where emp.salary =:salary and emp.name =:name")
    Employee findBySalaryIdAndName(@Param("name") String name, @Param("salary") Double Salary);

      Page<Employee> findByName(String name, Pageable pageable);

    @Override
    List<Employee> findAll();

    @Override
    @RestResource(exported = false)
    <S extends  Employee> S save(S s);

}
