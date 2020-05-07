package com.school.api.hr.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.api.hr.entity.EmployeeDesignation;

public interface IEmployeeDesignationRepository extends CrudRepository<EmployeeDesignation, String> {

}
