package com.capgemini.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) throws ClassNotFoundException, SQLException;
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<SavingsAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) throws ClassNotFoundException, SQLException;
	void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;
	void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;
	double showCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<SavingsAccount> sortAllSavingsAccount(int choice) throws ClassNotFoundException, SQLException;
	SavingsAccount updateAccount(SavingsAccount savingsAccount) throws ClassNotFoundException, SQLException, AccountNotFoundException;
}











