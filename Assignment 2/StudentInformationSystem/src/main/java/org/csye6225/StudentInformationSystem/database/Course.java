package org.csye6225.StudentInformationSystem.database;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Course")
public class Course {
	
	private String id;
	private String name;
	private String program;
	private String professor;
	private Set<String> students;
	private Set<String> announcements;
	
	@DynamoDBHashKey(attributeName = "Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBRangeKey(attributeName = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@DynamoDBAttribute(attributeName = "Program")
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	
	@DynamoDBAttribute(attributeName = "Professor")
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	@DynamoDBAttribute(attributeName = "Students List")
	public Set<String> getStudents() {
		return students;
	}
	public void setStudents(Set<String> students) {
		this.students = students;
	}
	
	@DynamoDBAttribute(attributeName = "announcement")
	public Set<String> getAnnouncements() {
		return announcements;
	}
	public void setAnnouncements(Set<String> announcements) {
		this.announcements = announcements;
	}
}
