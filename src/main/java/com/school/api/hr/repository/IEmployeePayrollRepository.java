package com.school.api.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.api.hr.entity.EmployeePayroll;

public interface IEmployeePayrollRepository extends CrudRepository<EmployeePayroll, String> {

	@Query(value = "SELECT entity FROM EmployeePayroll entity WHERE entity.status = :status")
	public List<EmployeePayroll> findPayrollByStatus(@Param("status") String status);

	@Query(value = "SELECT entity FROM EmployeePayroll entity WHERE entity.status = :status")
	public List<EmployeePayroll> findPayrollByEmployee(@Param("status") String status);

	@Query(value = "SELECT entity FROM EmployeePayroll entity WHERE entity.status = :status AND entity.employee.id = :empId")
	public List<EmployeePayroll> findPayrollByEmployeeAndStatus(@Param("status") String status,
			@Param("empId") String empId);

}
