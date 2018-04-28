package org.csye6225.StudentInformationSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramList{
	
	private Map<Integer, Program> map;
	
	public ProgramList() {
		map = new HashMap<>();
	}
	
	public List<Program> getList() {
		
		List<Program> list = new ArrayList<>();
		for (int key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;
		
	}
	
	public void addList(int id, Program program) {
		if (!map.containsKey(id)) {
			map.put(id, program);
		}
	}
	
	public void updateList(int id, Program program) {
		if (map.containsKey(id)) {
			map.put(id, program);
		}
	}
	
	public void deleteList(int id) {
		if (map.containsKey(id)) {
			map.remove(id);
		}
	}
	
}
