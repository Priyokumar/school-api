package com.school.api.hr.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.api.hr.entity.EmployeeAttendance;

public interface IEmployeeAttendanceRepository extends CrudRepository<EmployeeAttendance, String> {

	@Query(value = "SELECT entity FROM EmployeeAttendance entity WHERE entity.date = :date AND entity.employee.id = :empId")
	public Optional<EmployeeAttendance> findAttendanceByEmpAndDate(@Param("date") Date date,
			@Param("empId") String empId);

	@Query(value = "SELECT entity FROM EmployeeAttendance entity WHERE entity.employee.employeeType = :empType AND TO_CHAR(entity.date, 'MM') = :month AND TO_CHAR(entity.date, 'yyyy') = :year")
	public List<EmployeeAttendance> findAttendanceReport(@Param("empType") String empType, @Param("month") String month,
			@Param("year") String year);

}
