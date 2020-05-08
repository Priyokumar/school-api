package com.school.hr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.hr.entity.EmployeePayrollSalary;

public interface IEmployeeSalaryRepository extends CrudRepository<EmployeePayrollSalary, String> {

	@Query(value = "SELECT entity FROM EmployeePayrollSalary entity WHERE entity.employee.id = :empId")
	Optional<EmployeePayrollSalary> findByEmployee(@Param("empId") String empId);
}
