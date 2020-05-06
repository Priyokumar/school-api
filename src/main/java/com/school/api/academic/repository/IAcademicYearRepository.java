package com.school.api.academic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.school.api.academic.entity.AcademicYear;

public interface IAcademicYearRepository extends CrudRepository<AcademicYear, String> {

	@Query(value = "SELECT entity FROM AcademicYear entity WHERE entity.status = 'active'")
	public Optional<AcademicYear> findActiveAcademicYear();
}
