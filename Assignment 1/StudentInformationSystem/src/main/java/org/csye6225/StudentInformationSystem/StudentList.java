package org.csye6225.StudentInformationSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentList{
	
	private Map<Integer, Student> map;
	
	public StudentList() {
		map = new HashMap<>();
	}
	
	public List<Student> getList() {
		
		List<Student> list = new ArrayList<>();
		for (int key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;
		
	}
	
	public void addList(int id, Student student) {
		if (!map.containsKey(id)) {
			map.put(id, student);
		}
	}
	
	public void updateList(int id, Student student) {
		if (map.containsKey(id)) {
			map.put(id, student);
		}
	}
	
	public void deleteList(int id) {
		if (map.containsKey(id)) {
			map.remove(id);
		}
	}
	
}