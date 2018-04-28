package org.csye6225.StudentInformationSystem;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("program")
public class Management {
	
	//test case:
	static Program p1 = new Program(1, "CS");
	static Program p2 = new Program(2, "IS");
	static ProgramList pl = new ProgramList();
	
	static Course c1 = new Course(1, "CloudComputing", "CSYE6225");
	static Course c2 = new Course(2, "Web Tools", "INFO6500");
	static Course c3 = new Course(3, "Web Design", "INFO6200");
	static Course c4 = new Course(4, "Algorithm", "CSYE6150");
	static CourseList cl = new CourseList();
	
	static Student s1 = new Student(1, "Kevin", "/imgae/photoS1.jpg");
	static Student s2 = new Student(2, "Jack", "/image/photoS2.jpg");
	static StudentList sl = new StudentList();
	
	public Management() {
		// TODO Auto-generated constructor stub
		pl.addList(p1.getId(), p1);
		pl.addList(p2.getId(), p2);
		
		p1.getSl().addList(s1.getId(), s1);
		p2.getSl().addList(s2.getId(), s2);
		p1.getCl().addList(c1.getId(), c1);
		p1.getCl().addList(c3.getId(), c3);
		p2.getCl().addList(c2.getId(), c2);
		p2.getCl().addList(c4.getId(), c4);
		
		s1.setProgram(p1);
		s2.setProgram(p2);
		sl.addList(s1.getId(), s1);
		sl.addList(s2.getId(), s2);
		
		s1.getCl().addList(c1.getId(), c1);
		s1.getCl().addList(c2.getId(), c2);
		s2.getCl().addList(c3.getId(), c3);
		s2.getCl().addList(c4.getId(), c4);
		
		c1.getSl().addList(s1.getId(), s1);
		c2.getSl().addList(s2.getId(), s2);
		c3.getSl().addList(s1.getId(), s1);
		c4.getSl().addList(s2.getId(), s2);
		cl.addList(c1.getId(), c1);
		cl.addList(c2.getId(), c2);
		cl.addList(c3.getId(), c3);
		cl.addList(c4.getId(), c4);
	}
	
	@GET
	@Produces("application/json")
	public Response getAllPrograms() throws JSONException {
		
		JSONObject json = new JSONObject();
		JSONArray ja1 = new JSONArray();
		
		for (int i = 0; i < pl.getList().size(); i++) {
			ja1.put(pl.getList().get(i).getName());
			
		}
		
		json.put("ProgramName", ja1);
		
		String result = "@Produces(\"application/json\") Output: \n\n ProgramList: \n\n "+ json;
		return Response.status(200).entity(result).build();
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getProgramCourseList(@PathParam("id") int id) throws JSONException {
				
		JSONObject json = new JSONObject();
		json.put("Program", pl.getList().get(id - 1).getName());
		
		JSONArray ja1 = new JSONArray();
		for (int i = 0; i < pl.getList().get(id - 1).getCl().getList().size(); i++) {
			ja1.put(pl.getList().get(id - 1).getCl().getList().get(i).getName());
			
		}
		json.put("Course", ja1);
		
		JSONArray ja2 = new JSONArray();
		for (int i = 0; i < pl.getList().get(id - 1).getSl().getList().size(); i++) {
			ja2.put(pl.getList().get(id - 1).getSl().getList().get(i).getName());
			
		}
		json.put("Student", ja2);
		
		String result = "@Produces(\"application/json\") Output: \n\n Program: \n\n "+ json;
		return Response.status(200).entity(result).build();
	}
	
	@Path("{programid}/{courseid}")
	@GET
	@Produces("application/json")
	public Response getProgramCourse(@PathParam("programid") int programid, @PathParam("courseid") int courseid) throws JSONException {
				
		JSONObject json = new JSONObject();
		json.put("Course", pl.getList().get(programid - 1).getCl().getList().get(courseid - 1));
		
		String result = "@Produces(\"application/json\") Output: \n\n Program: \n\n "+ json;
		return Response.status(200).entity(result).build();
	}
	
	@Path("{id}")
	@POST
	@Consumes("application/json")
	public Response addCourse(@PathParam("id") int id) {
		pl.getList().get(id - 1).getCl().addList(5, new Course(5, "Database", "INFO6000"));
		
		String result = "Added";
		return Response.status(200).entity(result).build();
	}
	
	@Path("{programid}/{courseid}")
	@DELETE
	public Response deleteCourse(@PathParam("programid") int programid, @PathParam("courseid") int courseid) {
		pl.getList().get(programid - 1).getCl().deleteList(courseid);
		String result = "Deleted";
		return Response.status(200).entity(result).build();
	}
	
	@Path("{id}")
	@PUT
	@Consumes("application/json")
	public void updateCourse(@PathParam("id") int id) {
		pl.getList().get(id - 1).getCl().updateList(5, new Course(5, "Database", "INFO6000"));
	}
}
