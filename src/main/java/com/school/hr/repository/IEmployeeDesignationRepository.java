package com.school.hr.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.hr.entity.EmployeeDesignation;

public interface IEmployeeDesignationRepository extends CrudRepository<EmployeeDesignation, String> {

}
