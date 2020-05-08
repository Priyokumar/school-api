package com.school.settings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.school.settings.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, String> {

	@Query(value = "SELECT entity FROM Menu entity WHERE entity.role.id = :role")
	List<Menu> findByRole(@Param("role") String role);
}
