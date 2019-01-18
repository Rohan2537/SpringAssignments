package com.capgemini.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.CurrentAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public interface CurrentAccountDAO {
	CurrentAccount createNewAccount(CurrentAccount account) throws ClassNotFoundException, SQLException;
	CurrentAccount updateAccount(int accountNumber,int userChoice,String nameORSalary) throws ClassNotFoundException, SQLException;
	CurrentAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	CurrentAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	CurrentAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	CurrentAccount showCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> sortAllCurrentAccount(int choice) throws ClassNotFoundException, SQLException;
	void commit() throws SQLException;
}
