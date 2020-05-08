package com.school.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SC_EXAM_GRADE")
public class ExamGrade  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", nullable = false, length = 100)
	private String id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "GPA")
	private String gpa;
	
	@Column(name = "PC_FROM")
	private Double pcFrom;
	
	@Column(name = "PC_TO")
	private Double pcTo;
	
	@Column(name = "DESCRIPTION")
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public Double getPcFrom() {
		return pcFrom;
	}

	public void setPcFrom(Double pcFrom) {
		this.pcFrom = pcFrom;
	}

	public Double getPcTo() {
		return pcTo;
	}

	public void setPcTo(Double pcTo) {
		this.pcTo = pcTo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
