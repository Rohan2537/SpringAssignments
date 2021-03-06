package com.capgemini.moneymoney.application;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.moneymoney.account.ui.AccountCUI;


public class Bootstrap {
	/*
	 * static { try { Class.forName("com.mysql.jdbc.Driver"); Connection connection
	 * = (Connection) DriverManager.getConnection
	 * ("jdbc:mysql://localhost:3306/bankapp_db", "root", "root"); PreparedStatement
	 * preparedStatement = (PreparedStatement)
	 * connection.prepareStatement("DELETE FROM ACCOUNT");
	 * preparedStatement.execute(); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
	 */
	//}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		AccountCUI accountCUI = context.getBean(AccountCUI.class);
		accountCUI.start();
	}

}
