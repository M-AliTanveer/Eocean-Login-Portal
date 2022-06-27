package Studentlogin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Studentlogin.dao.StudentDAO;
import Studentlogin.model.Students;
@Service
public class StudentsServiceImpl implements StudentsService{
	@Autowired
	private StudentDAO studentDao;
	@Override
	@Transactional
	public void SaveStudent(Students student) {
		//StudentDAO = new StudentDAOImpl();
		
		studentDao.SaveStudent(student);
		
	}

	@Override
	@Transactional
	public void DeleteStudent(String email) {
		studentDao.DeleteStudent(email);
		
	}

	@Override
	@Transactional
	public Students GetStudent(String email) {
		return studentDao.GetStudent(email);	
		}

}
