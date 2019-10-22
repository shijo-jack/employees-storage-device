package com.springpoc.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpoc.demo.dao.CompanyRepository;
import com.springpoc.demo.entity.Company;
import com.springpoc.demo.entity.Employee;
import com.springpoc.demo.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Validated
@Api(value="Employee Service", produces="application/json")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CompanyRepository companyRepository;

	@ApiOperation(value="Retrieves the Employee Details of All the companies.")
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@ApiOperation(value="Save Employees for a specific company")
	@PostMapping("/employees/companyDetail/{companyId}")
	public void saveEmployeesForCompany(@PathVariable(name="companyId") int companyId,@Valid @RequestBody List<Employee> employees) {
		
		employees.stream().forEach( employee -> {
			employee.setId(0);
			employee.setCompanyId(companyRepository.findById(companyId).isPresent() ? companyRepository.findById(companyId).get() : null);
			employeeService.save(employee);
		});
		
	}
	
	@ApiOperation(value="Update an Employee for specific company")
	@PutMapping("/employees/companyDetail/{companyId}")
	public Employee updateEmployeeForCompany(@PathVariable(name="companyId") int companyId, @Valid @RequestBody Employee theEmployee) {
		
		Company company = companyRepository.findById(companyId).isPresent() ? companyRepository.findById(companyId).get() : null; 
		if(theEmployee.getCompanyId().equals(company)) {
			employeeService.save(theEmployee);
		}
		return theEmployee;
		
	}
	
	@ApiOperation(value="Delete an Employee for specific company")
	@DeleteMapping("/employees/companyDetail/{companyId}/{employeeId}")
	public String deleteEmployeeForCompany(@PathVariable(name="companyId") int companyId, @PathVariable int employeeId) {
		
		Company company = companyRepository.findById(companyId).isPresent() ? companyRepository.findById(companyId).get() : null;
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if(tempEmployee == null) {
			throw new RuntimeException("Employee Id Not found with id "+employeeId);
		}
		if(tempEmployee.getCompanyId().equals(company)) {
			employeeService.deleteById(employeeId);
		}
		
		return "Delete Employee Id "+ employeeId;
	}
	
	@ApiOperation(value="Compute average salary for employees of a company")
	@GetMapping("/averageSalary/companyDetail/{companyId}")
	public Double computeAvgSalary(@PathVariable(name="companyId") int companyId) {
		
		Company company = companyRepository.findById(companyId).isPresent() ? companyRepository.findById(companyId).get() : null;
		String companyName = company.getName();
		
		List<Employee> employeeList = employeeService.findAllByCompany(companyName);
		
		List<Float> salList = employeeList.stream().map(employee -> {
			return employee.getSalary();
		}).collect(Collectors.toList());
		
		
		return salList.stream().mapToDouble(a -> a).average().orElse(0.0);
	}
	
	// Additional APIs for generic use cases
	
	@ApiOperation(value="Retrieves the Employee Details of a specific company")
	@GetMapping("/employees/company/{company}")
	public List<Employee> findAllByCompany(@ApiParam(value="Name of the company", required= true) @PathVariable String company) {
		if(company == null || company.trim().isEmpty()) {
			throw new RuntimeException("company name is mandatory.");
		}
		return employeeService.findAllByCompany(company);
	}
	
	@ApiOperation(value="Retrieve detail of one specific employee.")
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@ApiParam(value="Employee Id", required= true) @PathVariable int employeeId) {
		if(employeeId <= 0) {
			throw new RuntimeException("Invalid Employee ID.");
		}
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null) {
			throw new RuntimeException("Employee Id Not found "+employeeId);
		}
		return theEmployee;
		
	}
	
	@ApiOperation(value="Save an Employee.")
	@PostMapping("/employees")
	public Employee addEmployee(@Valid @RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	@ApiOperation(value="Update Employee Details")
	@PutMapping("/employees")
	public Employee updateEmployee(@Valid @RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		return theEmployee;
		
	}
	
	@ApiOperation(value="Delete an Employee.")
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		if(employeeId <= 0) {
			throw new RuntimeException("Invalid employeeId");
		}
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if(tempEmployee == null) {
			throw new RuntimeException("Employee Id Not found with id "+employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Delete Employee Id "+ employeeId;
	}
	
}
