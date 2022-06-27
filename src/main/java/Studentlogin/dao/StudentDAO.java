package Studentlogin.dao;
import Studentlogin.model.Students;

public interface StudentDAO {
	
	public void SaveStudent(Students student);
	
	public void DeleteStudent(String email);
	
	public Students GetStudent(String email);
	
	
	
}
