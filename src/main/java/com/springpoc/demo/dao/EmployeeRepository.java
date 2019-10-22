package com.springpoc.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpoc.demo.entity.Company;
import com.springpoc.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findByCompanyIdEquals(Company company);
	
}
