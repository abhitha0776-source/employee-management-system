package com.jsp.ems.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.ems.entity.Employee;
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}
