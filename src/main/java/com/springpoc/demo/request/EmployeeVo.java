package com.springpoc.demo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeVo {
	
	private String name;
	private String surname;
	private String email;
	private String address;
	private int salary;

}
