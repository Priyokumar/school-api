package com.school.academic.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.academic.entity.Class;

public interface IClassRepository extends CrudRepository<Class, String> {

}
