package com.school.exam.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.exam.entity.ExamName;

public interface IExamNameRepository extends CrudRepository<ExamName, String>{

}
