package com.school.hr.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SC_EMP_ACADEMIC_BACKGROUND")
public class EmployeeAcademicBackground implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", nullable = false, length = 100)
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BOARD")
	private String board;

	@Column(name = "SCHOOL_INSTITUE")
	private String schoolInstitue;

	@Column(name = "START_YEAR")
	private Date startYear;

	@Column(name = "PASS_OUT_YEAR")
	private Date passOutYear;

	@Column(name = "SCORE")
	private String score;

	@Column(name = "HIGHEST_QUALIFICATION")
	private Boolean highestQualification;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private Employee employee;

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

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getSchoolInstitue() {
		return schoolInstitue;
	}

	public void setSchoolInstitue(String schoolInstitue) {
		this.schoolInstitue = schoolInstitue;
	}

	public Date getPassOutYear() {
		return passOutYear;
	}

	public void setPassOutYear(Date passOutYear) {
		this.passOutYear = passOutYear;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Boolean getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(Boolean highestQualification) {
		this.highestQualification = highestQualification;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getStartYear() {
		return startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

}
