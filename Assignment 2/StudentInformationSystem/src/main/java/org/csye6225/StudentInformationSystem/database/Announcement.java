package org.csye6225.StudentInformationSystem.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Announcement")
public class Announcement {
	
	private String id;
	private String professor;
	private String course;
	private String createdDate;
	private String content;
	
	@DynamoDBHashKey(attributeName = "Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName = "Professor")
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	@DynamoDBAttribute(attributeName = "Course")
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	@DynamoDBRangeKey(attributeName = "Date")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@DynamoDBAttribute(attributeName = "Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Announcement(String id, String professor, String course, String createdDate, String content) {
		this.id = id;
		this.professor = professor;
		this.course = course;
		this.createdDate = createdDate;
		this.content = content;
	}
	
}
