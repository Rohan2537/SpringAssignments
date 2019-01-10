package com.capgemini.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

@Repository
@Primary
public class SavingsAccountSJDAOImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[]{account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),account.getBankAccount().getAccountBalance(),
				account.isSalary(),null,"SA"});
		return account;
	}

	@Override
	public SavingsAccount updateAccount(int accountNumber, int userChoice, String nameORSalary)
			throws ClassNotFoundException, SQLException {
				
		return null;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount savingsAccount = template.queryForObject("SELECT * FROM account where account_id=?",new Object[] { accountNumber },new AccountMapper());
		return savingsAccount;
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		 return template.queryForObject("SELECT * FROM account where account_hn =?",
					new Object[] { accountHolderName }, new AccountMapper());
	}

	@Override
	public List<SavingsAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
	
		return template.query("SELECT * FROM account WHERE account_balance BETWEEN  ? AND ? ",
				new Object[] { minimumBalance, maximumBalance }, new AccountMapper());
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM account where account_id=?", new Object[] {accountNumber});
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		template.update("UPDATE ACCOUNT SET account_balance=? where account_number=?", accountNumber, currentBalance);
		
	}

	@Override
	public double showCurrentBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		double accountBalance = getAccountById(accountNumber).getBankAccount().getAccountBalance();
		return accountBalance;
	}

	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount savingsAccount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		template.update("UPDATE account SET account_hn=? , salary=? WHERE account_number=?",new Object[] { 
				savingsAccount.getBankAccount().getAccountHolderName(),savingsAccount.isSalary(),
						 	savingsAccount.getBankAccount().getAccountNumber()});
		return savingsAccount;
	}

}
