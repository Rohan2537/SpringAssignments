package com.capgemini.moneymoney.account.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

@Repository

public class SavingsAccountSJDAOImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) {
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[]{account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),account.getBankAccount().getAccountBalance(),
				account.isSalary(),null,"SA"});
		return account;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws AccountNotFoundException {
		SavingsAccount savingsAccount = template.queryForObject("SELECT * FROM account where account_id=?",new Object[] { accountNumber },new AccountMapper());
		return savingsAccount;
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws AccountNotFoundException {
		
		 return template.queryForObject("SELECT * FROM account where account_hn =?",
					new Object[] { accountHolderName }, new AccountMapper());
	}

	@Override
	public List<SavingsAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)
			throws AccountNotFoundException {
	
		return template.query("SELECT * FROM account WHERE account_balance BETWEEN  ? AND ? ",
				new Object[] { minimumBalance, maximumBalance }, new AccountMapper());
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) {
		template.update("DELETE FROM account where account_id=?", new Object[] {accountNumber});
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() {
		
		 return template.query("SELECT * FROM account WHERE type=? ",
				new Object[] {"SA"}, new AccountMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) {
		template.update("UPDATE ACCOUNT SET account_bal=? where account_id=?", currentBalance, accountNumber);
		
	}

	@Override
	public double showCurrentBalance(int accountNumber)
			throws AccountNotFoundException {
		double accountBalance = getAccountById(accountNumber).getBankAccount().getAccountBalance();
		return accountBalance;
	}

	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount savingsAccount)
			throws AccountNotFoundException {
		template.update("UPDATE account SET account_hn=? , salary=? WHERE account_id=?",new Object[] { 
				savingsAccount.getBankAccount().getAccountHolderName(),savingsAccount.isSalary(),
						 	savingsAccount.getBankAccount().getAccountNumber()});
		return savingsAccount;
	}

}
