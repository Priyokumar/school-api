package com.school.student.entity;

import org.hibernate.annotations.GenericGenerator;

import com.school.hr.entity.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SC_STUD_ATTENDANCE")
public class StudentAttendance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", nullable = false, length = 100)
	private String id;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "STUDENT")
	private Student student;

	@Column(name = "ATTENDANCE_TYPE")
	private String attendanceType;

	@Column(name = "DAY")
	private String note;

	@Column(name = "DATE")
	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getAttendanceType() {
		return attendanceType;
	}

	public void setAttendanceType(String attendanceType) {
		this.attendanceType = attendanceType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
