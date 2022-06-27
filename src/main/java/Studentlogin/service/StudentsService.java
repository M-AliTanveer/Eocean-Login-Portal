package Studentlogin.service;

import Studentlogin.model.Students;

public interface StudentsService {
	public void SaveStudent(Students student);
	
	public void DeleteStudent(String email);
	
	public Students GetStudent(String email);

}
