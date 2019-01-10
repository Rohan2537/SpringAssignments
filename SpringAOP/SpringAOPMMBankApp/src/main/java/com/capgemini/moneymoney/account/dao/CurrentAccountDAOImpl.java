/*
 * package com.capgemini.moneymoney.account.dao;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.sql.Statement;
 * import java.util.ArrayList; import java.util.List;
 * 
 * import com.capgemini.moneymoney.account.CurrentAccount; import
 * com.capgemini.moneymoney.account.util.DBUtil; import
 * com.capgemini.moneymoney.exception.AccountNotFoundException;
 * 
 * public class CurrentAccountDAOImpl implements CurrentAccountDAO {
 * 
 * public CurrentAccount createNewAccount(CurrentAccount account) throws
 * ClassNotFoundException, SQLException { Connection connection =
 * DBUtil.getConnection(); PreparedStatement preparedStatement =
 * connection.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)");
 * preparedStatement.setInt(1, account.getBankAccount().getAccountNumber());
 * preparedStatement.setString(2,
 * account.getBankAccount().getAccountHolderName());
 * preparedStatement.setDouble(3, account.getBankAccount().getAccountBalance());
 * preparedStatement.setBoolean(4, false); preparedStatement.setObject(5,
 * account.getodLimit()); preparedStatement.setString(6, "CA");
 * preparedStatement.executeUpdate(); preparedStatement.close();
 * DBUtil.commit(); return account; }
 * 
 * public List<CurrentAccount> getAllCurrentAccount() throws
 * ClassNotFoundException, SQLException { List<CurrentAccount> CurrentAccounts =
 * new ArrayList<>(); Connection connection = DBUtil.getConnection(); Statement
 * statement = connection.createStatement(); ResultSet resultSet =
 * statement.executeQuery("SELECT * FROM ACCOUNT WHERE account_type='CA'");
 * while (resultSet.next()) {// Check if row(s) is present in table int
 * accountNumber = resultSet.getInt(1); String accountHolderName =
 * resultSet.getString("account_hn"); double accountBalance =
 * resultSet.getDouble(3); double odLimit = resultSet.getDouble("odLimit");
 * CurrentAccount CurrentAccount = new CurrentAccount(accountNumber,
 * accountHolderName, accountBalance, odLimit);
 * CurrentAccounts.add(CurrentAccount); } DBUtil.commit(); return
 * CurrentAccounts; }
 * 
 * @Override public void updateBalance(int accountNumber, double currentBalance)
 * throws ClassNotFoundException, SQLException { Connection connection =
 * DBUtil.getConnection(); connection.setAutoCommit(false); PreparedStatement
 * preparedStatement = connection.prepareStatement
 * ("UPDATE ACCOUNT SET account_bal=? where account_id=?");
 * preparedStatement.setDouble(1, currentBalance); preparedStatement.setInt(2,
 * accountNumber); preparedStatement.executeUpdate(); DBUtil.commit(); }
 * 
 * @Override public CurrentAccount showCurrentBalance(int accountNumber)throws
 * ClassNotFoundException, SQLException,AccountNotFoundException { Connection
 * connection = DBUtil.getConnection(); PreparedStatement preparedStatement =
 * connection.prepareStatement ("SELECT * FROM account where account_id=?");
 * preparedStatement.setInt(1, accountNumber); ResultSet resultSet =
 * preparedStatement.executeQuery(); CurrentAccount CurrentAccount = null;
 * if(resultSet.next()) { String accountHolderName =
 * resultSet.getString("account_hn"); double accountBalance =
 * resultSet.getDouble(3); double odLimit = resultSet.getDouble("odLimit");
 * CurrentAccount = new CurrentAccount(accountNumber, accountHolderName,
 * accountBalance, odLimit); return CurrentAccount; } throw new
 * AccountNotFoundException("Account with account number "
 * +accountNumber+" does not exist."); }
 * 
 * @Override public CurrentAccount getAccountByName(String
 * accountHolderName)throws ClassNotFoundException,
 * SQLException,AccountNotFoundException { Connection connection =
 * DBUtil.getConnection(); PreparedStatement preparedStatement =
 * connection.prepareStatement ("SELECT * FROM account where account_hn=?");
 * preparedStatement.setString(1, accountHolderName); ResultSet resultSet =
 * preparedStatement.executeQuery(); CurrentAccount CurrentAccount = null;
 * if(resultSet.next()) { int accountNumber = resultSet.getInt("account_id");
 * double accountBalance = resultSet.getDouble(3); boolean salary =
 * resultSet.getBoolean("salary"); double odLimit =
 * resultSet.getDouble("odLimit"); CurrentAccount = new
 * CurrentAccount(accountNumber, accountHolderName, accountBalance, odLimit);
 * return CurrentAccount; } throw new
 * AccountNotFoundException("Account with account holder name "
 * +accountHolderName+" does not exist."); }
 * 
 * @Override public CurrentAccount getAccountById(int accountNumber) throws
 * ClassNotFoundException, SQLException, AccountNotFoundException { Connection
 * connection = DBUtil.getConnection(); PreparedStatement preparedStatement =
 * connection.prepareStatement ("SELECT * FROM account where account_id=?");
 * preparedStatement.setInt(1, accountNumber); ResultSet resultSet =
 * preparedStatement.executeQuery(); CurrentAccount CurrentAccount = null;
 * if(resultSet.next()) { String accountHolderName =
 * resultSet.getString("account_hn"); double accountBalance =
 * resultSet.getDouble(3); double odLimit = resultSet.getDouble("odLimit");
 * CurrentAccount = new CurrentAccount(accountNumber, accountHolderName,
 * accountBalance, odLimit); return CurrentAccount; } throw new
 * AccountNotFoundException("Account with account number "
 * +accountNumber+" does not exist."); }
 * 
 * @Override public List<CurrentAccount> getAccountsByBalance(double
 * minimumBalance,double maximumBalance) throws ClassNotFoundException,
 * SQLException,AccountNotFoundException { List<CurrentAccount> CurrentAccounts
 * = new ArrayList<>(); Connection connection = DBUtil.getConnection();
 * PreparedStatement preparedStatement = connection.prepareStatement
 * ("SELECT * FROM account WHERE account_bal BETWEEN ? AND ?");
 * preparedStatement.setDouble(1, minimumBalance);
 * preparedStatement.setDouble(2, maximumBalance); ResultSet resultSet =
 * preparedStatement.executeQuery(); while (resultSet.next()) {// Check if
 * row(s) is present in table int accountNumber = resultSet.getInt(1); String
 * accountHolderName = resultSet.getString("account_hn"); double accountBalance
 * = resultSet.getDouble(3); double odLimit = resultSet.getDouble("odLimit");
 * CurrentAccount currentAccount = new CurrentAccount(accountNumber,
 * accountHolderName, accountBalance, odLimit);
 * CurrentAccounts.add(currentAccount); } DBUtil.commit(); return
 * CurrentAccounts; }
 * 
 * public CurrentAccount updateAccount(int accountNumber, int userChoice,String
 * nameORSalary) throws ClassNotFoundException, SQLException {
 * 
 * if(userChoice==1){ Connection connection = DBUtil.getConnection();
 * PreparedStatement preparedStatement = connection.prepareStatement
 * ("UPDATE account SET account_hn=? where account_id=?");
 * preparedStatement.setString(1,nameORSalary); preparedStatement.setInt(2,
 * accountNumber); preparedStatement.executeUpdate(); DBUtil.commit(); } else
 * if(userChoice==2){ boolean salaryType = Boolean.parseBoolean(nameORSalary);
 * Connection connection = DBUtil.getConnection(); PreparedStatement
 * preparedStatement = connection.prepareStatement
 * ("UPDATE account SET salary=? where account_id=?");
 * preparedStatement.setBoolean(1, salaryType); preparedStatement.setInt(2,
 * accountNumber); preparedStatement.executeUpdate(); DBUtil.commit(); } return
 * null; }
 * 
 * @Override public CurrentAccount deleteAccount(int accountNumber) throws
 * ClassNotFoundException, SQLException { Connection connection =
 * DBUtil.getConnection(); PreparedStatement preparedStatement =
 * connection.prepareStatement ("DELETE FROM account where account_id=?");
 * preparedStatement.setInt(1, accountNumber); boolean accountToBeDeleted =
 * preparedStatement.execute(); DBUtil.commit(); return null; }
 * 
 * @Override public void commit() throws SQLException { DBUtil.commit(); }
 * 
 * @Override public List<CurrentAccount> sortAllCurrentAccount(int choice)throws
 * ClassNotFoundException, SQLException { List<CurrentAccount> CurrentAccounts =
 * new ArrayList<>(); Connection connection = DBUtil.getConnection();
 * PreparedStatement preparedStatement; ResultSet resultSet=null; switch
 * (choice) { case 1: preparedStatement = connection.prepareStatement
 * ("SELECT * FROM account ORDER BY account_id"); resultSet =
 * preparedStatement.executeQuery(); DBUtil.commit(); break; case 2:
 * preparedStatement = connection.prepareStatement
 * ("SELECT * FROM account ORDER BY account_hn"); resultSet =
 * preparedStatement.executeQuery(); DBUtil.commit(); break; case 3:
 * preparedStatement = connection.prepareStatement
 * ("SELECT * FROM account ORDER BY account_bal DESC"); resultSet =
 * preparedStatement.executeQuery(); DBUtil.commit(); break; }
 * 
 * while (resultSet.next()) {// Check if row(s) is present in table int
 * accountNumber = resultSet.getInt("account_id"); String accountHolderName =
 * resultSet.getString("account_hn"); double accountBalance =
 * resultSet.getDouble("account_bal"); double odLimit =
 * resultSet.getDouble("odLimit"); CurrentAccount currentAccount = new
 * CurrentAccount(accountNumber, accountHolderName, accountBalance, odLimit);
 * CurrentAccounts.add(currentAccount); }
 * 
 * return CurrentAccounts; }
 * 
 * 
 * 
 * }
 */