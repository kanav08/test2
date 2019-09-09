package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findById(int id);
	
	Employee deleteById(int id);
	public List<Employee> findAllByOrderByIdAsc();
}
