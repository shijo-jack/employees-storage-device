package com.springpoc.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = false, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(name="employee")
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	@NotBlank
	private int id;
	
	@NotBlank
	@ToString.Include
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@Column(name = "surname")
	@ToString.Include
	private String surname;
	
	@NotBlank
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Column(name = "address")
	private String address;
	
	@NotBlank
	@Column(name = "salary")
	private float salary;
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company companyId;
	
}
