package org.csye6225.StudentInformationSystem;

public class Course {
	
	private int id;
	private String name;
	private String alias;
	private Program program;
	private StudentList sl;
	
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public StudentList getSl() {
		return sl;
	}
	public void setSl(StudentList sl) {
		this.sl = sl;
	}
	
	public Course(int id, String name, String alias) {
		
		setId(id);
		setName(name);
		setAlias(alias);
		
		sl = new StudentList();
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", alias=" + alias + "]";
	}
	
	
}
