package com.school.api.academic.repository;

import org.springframework.data.repository.CrudRepository;
import com.school.api.academic.entity.Class;

public interface IClassRepository extends CrudRepository<Class, String> {

}
