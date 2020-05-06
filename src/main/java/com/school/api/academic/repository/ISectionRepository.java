package com.school.api.academic.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.api.academic.entity.Section;

public interface ISectionRepository  extends CrudRepository<Section, String>{

}
