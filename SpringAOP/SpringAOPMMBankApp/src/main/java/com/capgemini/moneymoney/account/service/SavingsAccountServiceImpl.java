package com.capgemini.moneymoney.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.dao.SavingsAccountDAO;
import com.capgemini.moneymoney.account.factory.AccountFactory;
import com.capgemini.moneymoney.exception.AccountNotFoundException;
import com.capgemini.moneymoney.exception.InsufficientFundsException;
import com.capgemini.moneymoney.exception.InvalidInputException;

@Service
@Transactional(rollbackFor= {Throwable.class})
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		
	}

	@Override
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	@Override
	public void deposit(SavingsAccount account, double amount) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}
	
	@Override
	public void withdraw(SavingsAccount account, double amount) {
		double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}

	@Override
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) {
		try {
			deposit(receiver, amount);
			withdraw(sender, amount);
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount savingsAccount) throws AccountNotFoundException {

		return savingsAccountDAO.updateAccount(savingsAccount);
	}
	
	@Override
	public SavingsAccount getAccountByName(String accountHolderName)throws AccountNotFoundException {
		
		return savingsAccountDAO.getAccountByName(accountHolderName);
	}
	
	@Override
	public SavingsAccount getAccountById(int accountNumber)throws AccountNotFoundException {
		
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public List<SavingsAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)throws AccountNotFoundException {
		return savingsAccountDAO.getAccountsByBalance(minimumBalance, maximumBalance);
	}
	
	@Override
	public SavingsAccount deleteAccount(int accountNumber) {
		
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public double showCurrentBalance(int accountNumber)throws AccountNotFoundException {
		
		return savingsAccountDAO.showCurrentBalance(accountNumber);
	}

	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice) {
	
		return savingsAccountDAO.sortAllSavingsAccount(choice);
	}	

}