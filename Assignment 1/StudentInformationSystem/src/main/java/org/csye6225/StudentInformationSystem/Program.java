package org.csye6225.StudentInformationSystem;

public class Program {
	
	private int id;
	private String name;
	private StudentList sl;
	private CourseList cl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public StudentList getSl() {
		return sl;
	}

	public void setSl(StudentList sl) {
		this.sl = sl;
	}

	public CourseList getCl() {
		return cl;
	}

	public void setCl(CourseList cl) {
		this.cl = cl;
	}

	public Program(int id, String name) {
		this.id = id;
		this.name = name;
		
		sl = new StudentList();
		cl = new CourseList();
	}
}
