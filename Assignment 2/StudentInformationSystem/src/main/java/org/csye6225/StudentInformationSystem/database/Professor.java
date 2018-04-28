package org.csye6225.StudentInformationSystem.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Professor")
public class Professor {
	
	private String id;
	private String name;
	private String course;
	
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
		
	@DynamoDBAttribute(attributeName = "Course")
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
