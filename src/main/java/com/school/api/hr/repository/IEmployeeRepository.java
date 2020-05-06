package com.school.api.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.api.hr.entity.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, String> {

	@Query(value = "SELECT entity FROM Employee entity WHERE entity.employeeType = :employeeType")
	public List<Employee> findEmployeeByType(@Param("employeeType") String employeeType);
}
