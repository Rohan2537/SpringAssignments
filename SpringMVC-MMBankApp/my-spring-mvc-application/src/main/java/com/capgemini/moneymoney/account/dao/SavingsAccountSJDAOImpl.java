package com.capgemini.moneymoney.account.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

@Repository

public class SavingsAccountSJDAOImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate template;
	
	public SavingsAccount createNewAccount(SavingsAccount account) {
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[]{account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),account.getBankAccount().getAccountBalance(),
				account.isSalary(),null,"SA"});
		return account;
	}

	public SavingsAccount getAccountById(int accountNumber)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = (SavingsAccount) template.queryForObject("SELECT * FROM account where account_id=?",new Object[] { accountNumber },new AccountMapper());
		return savingsAccount;
	}

	public SavingsAccount getAccountByName(String accountHolderName)
			throws AccountNotFoundException {
		
		 return (SavingsAccount) template.queryForObject("SELECT * FROM account where account_hn =?",
					new Object[] { accountHolderName }, new AccountMapper());
	}

	public List<SavingsAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)
			throws AccountNotFoundException {
	
		return template.query("SELECT * FROM account WHERE account_bal BETWEEN  ? AND ? ",
				new Object[] { minimumBalance, maximumBalance }, new AccountMapper());
	}

	public SavingsAccount deleteAccount(int accountNumber) {
		template.update("DELETE FROM account where account_id=?", new Object[] {accountNumber});
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount() {
		
		 return template.query("SELECT * FROM account WHERE account_type=? ",
				new Object[] {"SA"}, new AccountMapper());
	}

	public void updateBalance(int accountNumber, double currentBalance) {
		template.update("UPDATE ACCOUNT SET account_bal=? where account_id=?", currentBalance, accountNumber);
		
	}

	public double showCurrentBalance(int accountNumber)
			throws AccountNotFoundException {
		double accountBalance = getAccountById(accountNumber).getBankAccount().getAccountBalance();
		return accountBalance;
	}

	public List<SavingsAccount> sortAllSavingsAccount(int choice) {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		switch (choice) {
		case 1:
			template.queryForObject("SELECT * FROM account ORDER BY account_id", new Object[] {savingsAccounts}, new AccountMapper());
			break;

		case 2:
			template.queryForObject("SELECT * FROM account ORDER BY account_hn", new Object[] {savingsAccounts}, new AccountMapper());
			break;
			
		case 3:
			template.queryForObject("SELECT * FROM account ORDER BY account_bal DESC", new Object[] {savingsAccounts}, new AccountMapper());
			break;
		}
		 
		return savingsAccounts;
	}

	public SavingsAccount updateAccount(SavingsAccount savingsAccount)
			throws AccountNotFoundException {
		template.update("UPDATE account SET account_hn=? , salary=? WHERE account_id=?",new Object[] { 
				savingsAccount.getBankAccount().getAccountHolderName(),savingsAccount.isSalary(),
						 	savingsAccount.getBankAccount().getAccountNumber()});
		return savingsAccount;
	}

}
