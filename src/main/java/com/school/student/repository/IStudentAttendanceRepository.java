package com.school.student.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.student.entity.StudentAttendance;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IStudentAttendanceRepository extends CrudRepository<StudentAttendance, String> {

	@Query(value = "SELECT entity FROM StudentAttendance entity WHERE entity.date = :date AND entity.student.id = :studId")
	public Optional<StudentAttendance> findAttendanceByStudentAndDate(@Param("date") Date date,
			@Param("studId") String studId);

	@Query(value = "SELECT entity FROM StudentAttendance entity WHERE TO_CHAR(entity.date, 'MM') = :month AND TO_CHAR(entity.date, 'yyyy') = :year")
	public List<StudentAttendance> findAttendanceReport(@Param("month") String month, @Param("year") String year);
}
