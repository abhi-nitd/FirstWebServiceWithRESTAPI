package com.abhishek;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Student {
	@Id
	private int rollNumber;
	private String name;
	private String subject;
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String toString()
	{
		return "Student roll = "+rollNumber+" Name = "+name+" Subject = "+subject;
		
	}
	Student() {};
	
	
}
