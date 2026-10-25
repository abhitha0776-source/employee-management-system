package com.jsp.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ems.entity.Employee;
import com.jsp.ems.respository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public String createEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "employee data inserted";
	}
	public Object fetchByIdEmployee(int id) {
		Optional<Employee> oe = employeeRepository.findById(id);
		if(oe.isPresent()) {
			return oe.get();
		}
		else {
			return "no data found with id" +id;
		}
	}
	public List<Employee> fetchAll(){
		return employeeRepository.findAll();
	}
	
	public Object deleteByIdEmployee(int id) {
		employeeRepository.deleteById(id);
		return "data deleted by id";
	}
	public String deleteAll() {
		employeeRepository.deleteAll();
		return "data deleted";
	}
	

}
