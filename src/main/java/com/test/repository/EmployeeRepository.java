package com.test.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	 // List<Tutorial> findByPublished(boolean published);
	  List<Employee> findByNameContaining(String name);
	}
