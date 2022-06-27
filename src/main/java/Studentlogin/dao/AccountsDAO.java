package Studentlogin.dao;
import Studentlogin.model.Accounts;

public interface AccountsDAO {

	public void SaveAccount(Accounts account);
	
	//public void DeleteAccount(String username);
	
	public void UpdateAccount(Accounts account);
	
	public Accounts GetAccount(String email);
}
