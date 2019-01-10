package com.capgemini.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.dao.SavingsAccountDAO;
import com.capgemini.moneymoney.account.factory.AccountFactory;
import com.capgemini.moneymoney.account.util.DBUtil;
import com.capgemini.moneymoney.exception.AccountNotFoundException;
import com.capgemini.moneymoney.exception.InsufficientFundsException;
import com.capgemini.moneymoney.exception.InvalidInputException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		//savingsAccountDAO = new SavingsAccountDAOImpl();
	}

	@Override
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	@Override
	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		}else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}
	
	@Override
	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		} else {
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}
	}

	@Override
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		try {
			withdraw(sender, amount);
			deposit(receiver, amount);
			DBUtil.commit();
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
			DBUtil.rollback();
		} catch(Exception e) {
			e.printStackTrace();
			DBUtil.rollback();
		}
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount savingsAccount) throws ClassNotFoundException, SQLException, AccountNotFoundException {

		return savingsAccountDAO.updateAccount(savingsAccount);
	}
	
	@Override
	public SavingsAccount getAccountByName(String accountHolderName)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return savingsAccountDAO.getAccountByName(accountHolderName);
	}
	
	@Override
	public SavingsAccount getAccountById(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public List<SavingsAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		return savingsAccountDAO.getAccountsByBalance(minimumBalance, maximumBalance);
	}
	
	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public double showCurrentBalance(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return savingsAccountDAO.showCurrentBalance(accountNumber);
	}

	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice)throws ClassNotFoundException, SQLException {
	
		return savingsAccountDAO.sortAllSavingsAccount(choice);
	}	

}