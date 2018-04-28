package org.csye6225.StudentInformationSystem;

public class Board {
	
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
	
	public Board(int id, String content) {
		this.id = id;
		this.content = content;
	}
}
