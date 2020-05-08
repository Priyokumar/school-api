package com.school.academic.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.academic.entity.Section;

public interface ISectionRepository extends CrudRepository<Section, String> {

}
