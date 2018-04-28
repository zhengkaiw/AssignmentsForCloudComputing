package org.csye6225.StudentInformationSystem.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Path("management")
public class Management {
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	
	Announcement ann = new Announcement("003", "Brett", "INFO6250", "Apr 1 2018", "Next Assignment");
	//Student part
	@Path("student/{id}")
	@GET
	@Produces("application/json")
	public Response getStudent(@PathParam("id") int id) {
		//DynamoDBMapper mapper = new DynamoDBMapper(client);
		//Student student = mapper.load(Student.class, id);
		String student = "123";
		return Response.status(200).entity(student).build();
	}
	
	@Path("student/add")
	@POST
	@Consumes("application/json")
	public void addStudent(Student student) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(student);
		Course course = mapper.load(Course.class, student.getCourses());
		List<String> students = new ArrayList<String>();
        students.add(student.getId());
        course.setStudents(new HashSet<String>(students));
		mapper.save(course);
	}
	
	@Path("student/{id}")
	@DELETE
	public void deleteStudent(@PathParam("id") int id) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Student student = mapper.load(Student.class, id);
		mapper.delete(student);
	}
	
	//Announcement partß
	@Path("course/{id}/announcement")
	@POST
	@Consumes("application/json")
	public void addAnnouncement(@PathParam("id") String id) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(ann);
		Course course = mapper.load(Course.class, id);
		List<String> announcements = new ArrayList<String>();
		announcements.add(ann.getId());
        course.setStudents(new HashSet<String>(announcements));
		mapper.save(course);
	}
}
