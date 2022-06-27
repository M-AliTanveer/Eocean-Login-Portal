package Studentlogin.dao;

import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Studentlogin.model.Students;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory session;
	private Logger logger = Logger.getLogger(StudentDAOImpl.class);
	@Override
	@Transactional
	public void SaveStudent(Students student) {
		try 
		{
		   logger.info("Entered StudentDAOImpl::SaveStudent DAO.");
		   Session currSession = session.getCurrentSession();
		   currSession.save(student);
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentDAOImpl::SaveStudent DAO: " + e.toString());

		}
		logger.info("Exited StudentDAOImpl::SaveAccount DAO.");

	}

	@Override
	@Transactional
	public void DeleteStudent(String email) {
		try 
		{
			logger.info("Entered StudentDAOImpl::DeleteStudent DAO.");
			Session currSession = session.getCurrentSession();
	        Students book = currSession.byId(Students.class).load(email);
	        currSession.delete(book);
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentDAOImpl::DeleteStudent DAO: " + e.toString());

		}
		logger.info("Exited StudentDAOImpl::DeleteStudent DAO.");

	}

	@Override
	@Transactional
	public Students GetStudent(String email) {
		Students st = null;
		try
		{
			logger.info("Entered StudentDAOImpl::GetStudent DAO.");

		Session currSession = session.getCurrentSession();
		st = currSession.get(Students.class, email);
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentDAOImpl::GetStudent DAO: " + e.toString());

		}
		logger.info("Exited StudentDAOImpl::GetStudent DAO.");
		return st;

	}

}
