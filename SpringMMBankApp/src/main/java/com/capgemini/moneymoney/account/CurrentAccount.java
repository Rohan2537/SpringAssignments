package com.capgemini.moneymoney.account;

public class CurrentAccount {
	private double odLimit;
	private BankAccount bankAccount;

	public CurrentAccount(String accountHolderName, double accountBalance, double odLimit) {
		System.out.println("from current account: "+accountBalance);
		bankAccount = new BankAccount(accountHolderName, accountBalance);
		this.odLimit = odLimit;
	}
	public CurrentAccount(String accountHolderName, double odLimit) {
		bankAccount = new BankAccount(accountHolderName);
		this.odLimit = odLimit;
	}

	public CurrentAccount(int accountNumber, String accountHolderName, double accountBalance, double odLimit) {
		bankAccount = new BankAccount(accountNumber, accountHolderName, accountBalance);
		this.odLimit = odLimit;
	}
	public double getodLimit() {
		return odLimit;
	}

	public void setodLimit(double odLimit) {
		this.odLimit = odLimit;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		return "CurrentAccount [odLimit=" + odLimit + ", bankAccount=" + bankAccount + "]";
	}
}
