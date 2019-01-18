package com.capgemini.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.moneymoney.account.CurrentAccount;
import com.capgemini.moneymoney.account.dao.CurrentAccountDAO;
import com.capgemini.moneymoney.account.factory.AccountFactory;
import com.capgemini.moneymoney.exception.AccountNotFoundException;
import com.capgemini.moneymoney.exception.InsufficientFundsException;
import com.capgemini.moneymoney.exception.InvalidInputException;

public class CurrentAccountServiceImpl implements CurrentAccountService {

	private AccountFactory factory;
	private CurrentAccountDAO CurrentAccountDAO;

	public CurrentAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		//CurrentAccountDAO = new CurrentAccountDAOImpl();
	}

	public CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odLimit)
			throws ClassNotFoundException, SQLException {
		CurrentAccount account = factory.createNewCurrentAccount(accountHolderName, accountBalance, odLimit);
		CurrentAccountDAO.createNewAccount(account);
		return null;
	}

	public List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException {
		return CurrentAccountDAO.getAllCurrentAccount();
	}

	public void deposit(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			CurrentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//CurrentAccountDAO.commit();
		}else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}
	
	public void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			CurrentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//CurrentAccountDAO.commit();
		} else {
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}
	}

	public void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		try {
			withdraw(sender, amount);
			deposit(receiver, amount);
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CurrentAccount updateAccount(int accountNumber,int userChoice,String nameORSalary) throws ClassNotFoundException, SQLException {
		return CurrentAccountDAO.updateAccount(accountNumber, userChoice,nameORSalary);
	}

	@Override
	public CurrentAccount getAccountByName(String accountHolderName)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return CurrentAccountDAO.getAccountByName(accountHolderName);
	}
	
	@Override
	public CurrentAccount getAccountById(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return CurrentAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public List<CurrentAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		return CurrentAccountDAO.getAccountsByBalance(minimumBalance, maximumBalance);
	}
	
	@Override
	public CurrentAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		
		return CurrentAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public CurrentAccount showCurrentBalance(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return CurrentAccountDAO.showCurrentBalance(accountNumber);
	}

	@Override
	public List<CurrentAccount> sortAllCurrentAccount(int choice)throws ClassNotFoundException, SQLException {
	
		return CurrentAccountDAO.sortAllCurrentAccount(choice);
	}
	

}