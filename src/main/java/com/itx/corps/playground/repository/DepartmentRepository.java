package com.itx.corps.playground.repository;

import com.itx.corps.playground.domain.Department;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, String> {

}
