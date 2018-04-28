package org.csye6225.StudentInformationSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseList{
	
	private Map<Integer, Course> map;
	
	public CourseList() {
		map = new HashMap<>();
	}
	
	public List<Course> getList() {
		
		List<Course> list = new ArrayList<>();
		for (int key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;
		
	}
	
	public void addList(int id, Course course) {
		if (!map.containsKey(id)) {
			map.put(id, course);
		}
	}
	
	public void updateList(int id, Course course) {
		if (map.containsKey(id)) {
			map.put(id, course);
		}
	}
	
	public void deleteList(int id) {
		if (map.containsKey(id)) {
			map.remove(id);
		}
	}
	
}