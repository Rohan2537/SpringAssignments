package com.capgemini.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account);
	SavingsAccount getAccountById(int accountNumber) throws AccountNotFoundException;
	SavingsAccount getAccountByName(String accountHolderName) throws AccountNotFoundException;
	List<SavingsAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber);
	List<SavingsAccount> getAllSavingsAccount();
	void updateBalance(int accountNumber, double currentBalance);
	double showCurrentBalance(int accountNumber)throws AccountNotFoundException;
	List<SavingsAccount> sortAllSavingsAccount(int choice) ;
	SavingsAccount updateAccount(SavingsAccount savingsAccount) throws AccountNotFoundException;
	
}