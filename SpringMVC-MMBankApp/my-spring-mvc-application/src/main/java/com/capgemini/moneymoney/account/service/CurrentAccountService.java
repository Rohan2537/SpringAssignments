package com.capgemini.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.CurrentAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public interface CurrentAccountService {
	CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odLimit) throws ClassNotFoundException, SQLException;
	CurrentAccount updateAccount(int accountNumber,int userChoice,String nameORSalary) throws ClassNotFoundException, SQLException;
	CurrentAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	CurrentAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws ClassNotFoundException, SQLException, AccountNotFoundException;
    CurrentAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException;
	void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount) throws ClassNotFoundException, SQLException;
	void deposit(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException;
	void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException;
	CurrentAccount showCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> sortAllCurrentAccount(int choice) throws ClassNotFoundException, SQLException;
}
