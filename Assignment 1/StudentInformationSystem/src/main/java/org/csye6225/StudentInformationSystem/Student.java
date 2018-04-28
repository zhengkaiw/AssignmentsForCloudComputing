package org.csye6225.StudentInformationSystem;

public class Student {

	private int id;
	private String name;
	private String img;
	private Program program;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public CourseList getCl() {
		return cl;
	}
	public void setCl(CourseList cl) {
		this.cl = cl;
	}
	
	public Student(int id, String name, String img) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.img = img;
		
		cl = new CourseList();
	}
}
