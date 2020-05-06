package com.school.api.hr.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.api.hr.entity.Designation;

public interface IDesignationRepository extends CrudRepository<Designation, String>{

}
