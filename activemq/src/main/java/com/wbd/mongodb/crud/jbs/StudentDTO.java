package com.wbd.mongodb.crud.jbs;

import java.io.Serializable;

public class StudentDTO implements Serializable{

	public StudentDTO(String studentId, String course, double score) {
		super();
		this.studentId = studentId;
		this.course = course;
		this.score = score;
	}

	public StudentDTO() {
		super();
	}

	private String studentId;
	
	private String course;
	
	private double score;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
}
