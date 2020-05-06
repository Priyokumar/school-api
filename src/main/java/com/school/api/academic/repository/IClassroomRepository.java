package com.school.api.academic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.api.academic.entity.ClassRoom;

public interface IClassroomRepository extends CrudRepository<ClassRoom, String> {

	@Query(value = "SELECT entity FROM ClassRoom entity WHERE entity.status = :status")
	public List<ClassRoom> findClassRoomsByStatus(@Param("status") String status);

}
