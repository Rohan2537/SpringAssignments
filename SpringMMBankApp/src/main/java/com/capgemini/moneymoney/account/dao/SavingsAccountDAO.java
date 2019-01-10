package com.capgemini.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	SavingsAccount updateAccount(int accountNumber,int userChoice,String nameORSalary) throws ClassNotFoundException, SQLException;
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<SavingsAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	double showCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<SavingsAccount> sortAllSavingsAccount(int choice) throws ClassNotFoundException, SQLException;
	void commit() throws SQLException;
	SavingsAccount updateAccount(SavingsAccount savingsAccount) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
}