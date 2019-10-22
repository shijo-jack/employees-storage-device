package com.springpoc.demo.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRequest {
	
	int companyId;
	List<EmployeeVo> employees;

}
