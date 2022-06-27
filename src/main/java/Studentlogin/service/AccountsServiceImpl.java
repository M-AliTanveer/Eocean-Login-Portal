package Studentlogin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Studentlogin.dao.AccountsDAO;
import Studentlogin.model.Accounts;
@Service
public class AccountsServiceImpl implements AccountsService{
	@Autowired
	private AccountsDAO accountDAO;
	@Override
	@Transactional
	public void SaveAccount(Accounts account) {
		accountDAO.SaveAccount(account);
	}
	@Override
	@Transactional
	public void UpdateAccount(Accounts account) {
		 accountDAO.UpdateAccount(account);
	}
	@Override
	@Transactional
	public Accounts GetAccount(String email) {
		return accountDAO.GetAccount(email);
	}

}
