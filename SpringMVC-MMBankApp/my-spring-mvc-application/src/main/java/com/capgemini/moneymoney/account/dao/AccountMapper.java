package com.capgemini.moneymoney.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capgemini.moneymoney.account.SavingsAccount;

public class AccountMapper implements RowMapper {
	 
	public SavingsAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		SavingsAccount savingsAccount = new SavingsAccount(resultSet.getInt("account_id"),
				resultSet.getString("account_hn"), resultSet.getDouble("account_bal"),
				 resultSet.getBoolean("salary"));
		return savingsAccount;
	}

}