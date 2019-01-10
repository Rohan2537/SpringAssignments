package com.capgemini.moneymoney.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.util.DBUtil;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

public class SavingsAccountDAOImpl implements SavingsAccountDAO {

	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)");
		preparedStatement.setInt(1, account.getBankAccount().getAccountNumber());
		preparedStatement.setString(2, account.getBankAccount().getAccountHolderName());
		preparedStatement.setDouble(3, account.getBankAccount().getAccountBalance());
		preparedStatement.setBoolean(4, account.isSalary());
		preparedStatement.setObject(5, null);
		preparedStatement.setString(6, "SA");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DBUtil.commit();
		return account;
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT WHERE account_type='SA'");
		while (resultSet.next()) {// Check if row(s) is present in table
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			savingsAccounts.add(savingsAccount);
		}
		DBUtil.commit();
		return savingsAccounts;
	}
	
	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement = connection.prepareStatement
				("UPDATE ACCOUNT SET account_bal=? where account_id=?");
		preparedStatement.setDouble(1, currentBalance);
		preparedStatement.setInt(2, accountNumber);
		preparedStatement.executeUpdate();
		DBUtil.commit();
	}
	
	@Override
	public double showCurrentBalance(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			return accountBalance;
		}
		 throw new AccountNotFoundException("Account with account number "+accountNumber+" does not exist.");
	}
	
	@Override
	public SavingsAccount getAccountByName(String accountHolderName)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account where account_hn=?");
		preparedStatement.setString(1, accountHolderName);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			int accountNumber = resultSet.getInt("account_id");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			return savingsAccount;
		}
		 throw new AccountNotFoundException("Account with account holder name "+accountHolderName+" does not exist.");
	}
	
	@Override
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			return savingsAccount;
		}
		throw new AccountNotFoundException("Account with account number "+accountNumber+" does not exist.");
	}
	
	@Override
	public List<SavingsAccount> getAccountsByBalance(double minimumBalance,double maximumBalance) throws ClassNotFoundException, SQLException,AccountNotFoundException {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account WHERE account_bal BETWEEN ? AND ?");
		preparedStatement.setDouble(1, minimumBalance);
		preparedStatement.setDouble(2, maximumBalance);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {// Check if row(s) is present in table
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			savingsAccounts.add(savingsAccount);
		}
		DBUtil.commit();
		return savingsAccounts;
	}
	
	public SavingsAccount updateAccount(int accountNumber, int userChoice,String nameORSalary) throws ClassNotFoundException, SQLException {
		
		if(userChoice==1){
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement
					("UPDATE account SET account_hn=? where account_id=?");
		preparedStatement.setString(1,nameORSalary);
		preparedStatement.setInt(2, accountNumber);
		preparedStatement.executeUpdate();
		DBUtil.commit();
		}
		else if(userChoice==2){
			boolean salaryType = Boolean.parseBoolean(nameORSalary);
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement
					("UPDATE account SET salary=? where account_id=?");
			preparedStatement.setBoolean(1, salaryType);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.executeUpdate();
			DBUtil.commit();
		}
		return null;
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("DELETE FROM account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		boolean accountToBeDeleted = preparedStatement.execute();
		DBUtil.commit();
		return null;
	}

	@Override
	public void commit() throws SQLException {
		DBUtil.commit();
	}

	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice)throws ClassNotFoundException, SQLException {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet=null;
		switch (choice) {
		case 1:
			 preparedStatement = connection.prepareStatement
			("SELECT * FROM account ORDER BY account_id");
			  resultSet = preparedStatement.executeQuery();
			  DBUtil.commit();
			break;
		case 2:
			 preparedStatement = connection.prepareStatement
			("SELECT * FROM account ORDER BY account_hn");
			 resultSet = preparedStatement.executeQuery();
			 DBUtil.commit();
			break;
		case 3:
			 preparedStatement = connection.prepareStatement
			("SELECT * FROM account ORDER BY account_bal DESC");
			 resultSet = preparedStatement.executeQuery();
			 DBUtil.commit();
			break;
		}
		
		while (resultSet.next()) {// Check if row(s) is present in table
			int accountNumber = resultSet.getInt("account_id");
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble("account_bal");
			boolean salary = resultSet.getBoolean("salary");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			savingsAccounts.add(savingsAccount);
		}
		
		return savingsAccounts;
	}

	@Override
		public SavingsAccount updateAccount(SavingsAccount savingsAccount)
			    throws ClassNotFoundException, SQLException, AccountNotFoundException
			  {
			    Connection connection = DBUtil.getConnection();
			    PreparedStatement preparedStatement = connection.prepareStatement(
			      "UPDATE account SET account_hn=? , salary=? WHERE account_id=?");
			    preparedStatement.setString(1, savingsAccount.getBankAccount().getAccountHolderName());
			    preparedStatement.setBoolean(2, savingsAccount.isSalary());
			    preparedStatement.setInt(3, savingsAccount.getBankAccount().getAccountNumber());
			    preparedStatement.executeUpdate();
			    DBUtil.commit();
			    return getAccountById(savingsAccount.getBankAccount().getAccountNumber());
			  }

}