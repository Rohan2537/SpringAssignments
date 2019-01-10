package com.capgemini.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary);
	SavingsAccount getAccountById(int accountNumber) throws AccountNotFoundException;
	SavingsAccount getAccountByName(String accountHolderName) throws AccountNotFoundException;
	List<SavingsAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber);
	List<SavingsAccount> getAllSavingsAccount();
	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount);
	void deposit(SavingsAccount account, double amount);
	void withdraw(SavingsAccount account, double amount);
	double showCurrentBalance(int accountNumber) throws AccountNotFoundException;
	List<SavingsAccount> sortAllSavingsAccount(int choice);
	SavingsAccount updateAccount(SavingsAccount savingsAccount) throws AccountNotFoundException;
}











