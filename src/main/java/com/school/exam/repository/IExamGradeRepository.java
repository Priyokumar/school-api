package com.school.exam.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.exam.entity.ExamGrade;

public interface IExamGradeRepository extends CrudRepository<ExamGrade, String>{

}
