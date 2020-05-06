package com.school.api.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.api.hr.entity.Payroll;

public interface IPayrollRepository extends CrudRepository<Payroll, String> {

	@Query(value = "SELECT entity FROM Payroll entity WHERE entity.status = :status")
	public List<Payroll> findPayrollByStatus(@Param("status") String status);

	@Query(value = "SELECT entity FROM Payroll entity WHERE entity.status = :status")
	public List<Payroll> findPayrollByEmployee(@Param("status") String status);

	@Query(value = "SELECT entity FROM Payroll entity WHERE entity.status = :status AND entity.employee.id = :empId")
	public List<Payroll> findPayrollByEmployeeAndStatus(@Param("status") String status, @Param("empId") String empId);
	
}
