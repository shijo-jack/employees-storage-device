package com.springpoc.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpoc.demo.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	public Company findByNameEquals(String comapanyName);

}
