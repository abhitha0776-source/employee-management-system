package com.jsp.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ems.entity.Employee;
import com.jsp.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public String creatEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/{id}")
	public Object fetchByIdEmployee(@PathVariable int id) {
		return employeeService.fetchByIdEmployee(id);
	}
	
	@GetMapping
	public List<Employee> fetchAll(){
		return employeeService.fetchAll();
	}
	
	@DeleteMapping("/{id}")
	public Object deleteByIdEmployee(@PathVariable  int id) {
		return employeeService.deleteByIdEmployee(id);
	}
	
	@DeleteMapping
	public String deleteAll() {
		return employeeService.deleteAll();
	}

}
