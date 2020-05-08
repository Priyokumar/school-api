package com.school.academic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.academic.entity.Subject;

public interface ISubjectRepository extends CrudRepository<Subject, String> {

	@Query(value = "SELECT entity FROM Subject entity WHERE entity.isTheory = :isTheory")
	public List<Subject> findClassRoomsByIsTheory(@Param("isTheory") Boolean isTheory);
}
