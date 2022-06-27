package Studentlogin.dao;

import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Studentlogin.controller.StudentController;
import Studentlogin.model.Accounts;

@Repository
public class AccountsDAOImpl implements AccountsDAO{

	@Autowired
	SessionFactory session;
	private Logger logger = Logger.getLogger(AccountsDAOImpl.class);
	@Override
	@Transactional
	public void SaveAccount(Accounts account) {
		try 
		{
			logger.info("Entered AccountsDAOImpl::SaveAccount DAO.");
			Session currSession = session.getCurrentSession();
			currSession.save(account);
		}
		catch(Exception e)
		{
			logger.error("Exception in AccountsDAOImpl::SaveAccount DAO: " + e.toString());
		}
		logger.info("Exited AccountsDAOImpl::SaveAccount DAO.");

	}

	@Override
	@Transactional
	public Accounts GetAccount(String email) {
		Accounts acc = null;;
		try 
		{
			
			logger.info("Entered AccountsDAOImpl::GetAccount DAO.");
			Session currSession = session.getCurrentSession();
			 acc = currSession.get(Accounts.class, email);
		}
		catch(Exception e)
		{
			logger.error("Exception in AccountsDAOImpl::GetAccount DAO: " + e.toString());
		}
			logger.info("Exited AccountsDAOImpl::GetAccount DAO.");
		return acc;
	}

	@Override
	@Transactional
	public void UpdateAccount(Accounts account) {
		try 
		{
			logger.info("Entered AccountsDAOImpl::UpdateAccount DAO.");
			Session currSession = session.getCurrentSession();
			currSession.update(account);
		}
		catch(Exception e)
		{
			logger.error("Exception in AccountsDAOImpl::UpdateAccount DAO: " + e.toString());

		}
		logger.info("Exited AccountsDAOImpl::UpdateAccount DAO.");

		
	}

}
