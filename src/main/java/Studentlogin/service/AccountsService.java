package Studentlogin.service;

import Studentlogin.model.Accounts;

public interface AccountsService {
	public void SaveAccount(Accounts account);
	
	public void UpdateAccount(Accounts account);
	
	public Accounts GetAccount(String email);

}
