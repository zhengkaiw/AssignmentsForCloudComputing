package org.csye6225.StudentInformationSystem;

public class Note {
	
	private int id;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Note(int id, String content) {
		this.id = id;
		this.content = content;
	}
}
