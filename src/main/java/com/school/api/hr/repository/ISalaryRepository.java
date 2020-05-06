package com.school.api.hr.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.api.hr.entity.Salary;

public interface ISalaryRepository extends CrudRepository<Salary, String>{

	@Query(value = "SELECT entity FROM Salary entity WHERE entity.employee.id = :empId")
	Optional<Salary> findByEmployee(@Param("empId") String empId);
}
