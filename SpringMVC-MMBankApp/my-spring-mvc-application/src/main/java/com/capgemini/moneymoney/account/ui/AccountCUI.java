package com.capgemini.moneymoney.account.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.moneymoney.account.CurrentAccount;
import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.service.CurrentAccountService;
import com.capgemini.moneymoney.account.service.SavingsAccountService;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

@Component
public class AccountCUI {
	private Scanner scanner = new Scanner(System.in);
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	private CurrentAccountService currentAccountService;
	public void start() {
		
		do {
			System.out.println("****** Welcome to Money Money Bank********");
			System.out.println("1. Open New Savings Account");
			System.out.println("2. Update Account");
			System.out.println("3. Close Account");
			System.out.println("4. Search Account");
			System.out.println("5. Withdraw");
			System.out.println("6. Deposit");
			System.out.println("7. FundTransfer");
			System.out.println("8. Check Current Balance");
			System.out.println("9. Get All Account Details");
			System.out.println("10. Sort Accounts");
			System.out.println("11. Exit");
			System.out.println();
			System.out.println("Make your choice: ");
			
			int choice = scanner.nextInt();
			
			performOperation(choice);
			
		} while(true);
	}

	private void performOperation(int choice) {
		
		switch (choice) {
		case 1:
			acceptInput();
			break;
		case 2:
			updateAccount();
			break;
		case 3:
			closeAccount();
			break;
		case 4:
			searchAccount();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			deposit();
			break;
		case 7:
			fundTransfer();
			break;
		case 8:
			showCurrentBalance();
			break;
		case 9:
			showAllAccounts();
			break;
		case 10:
			sortAccounts();
			break;
		case 11:
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice!");
			break;
		}
		
	}

	private void acceptInput() {
		System.out.println("Create new Account: \n 1.Savings Account \n 2.Current Account");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Enter your Full Name: ");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			System.out.println("Enter Initial Balance(type na for Zero Balance): ");
			String accountBalanceStr = scanner.next();
			double accountBalance=0.0;
			if(!accountBalanceStr.equalsIgnoreCase("na")) {
				accountBalance = Double.parseDouble(accountBalanceStr);
			}
			System.out.println("Salaried?(y/n): ");
			boolean salary = scanner.next().equalsIgnoreCase("n")?false:true;
			createSavingsAccount(accountHolderName,accountBalance, salary);
		
			break;

	    case 2:
	    	System.out.println("Enter your Full Name: ");
			String currentAccountHolderName = scanner.nextLine();
			currentAccountHolderName = scanner.nextLine();
			System.out.println("Enter Initial Balance(type na for Zero Balance): ");
			String currentAccountBalanceString = scanner.next();
			double currentAccountBalance=0.0;
			if(!currentAccountBalanceString.equalsIgnoreCase("na")) {
				currentAccountBalance = Double.parseDouble(currentAccountBalanceString);
			}
			System.out.println("Enter odLimit ");
			double odLimit = scanner.nextDouble();
			try {
				currentAccountService.createNewAccount(currentAccountHolderName, currentAccountBalance, odLimit);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	    	break;
		}
		
	}
	private void createSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
	}
	
	private void createCurrentAccount(String accountHolderName, double accountBalance, double odLimit) {
		try {
			currentAccountService.createNewAccount(accountHolderName, accountBalance, odLimit);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateAccount() {
		
		System.out.println("Enter a Account number ");
	    int accountNumber = scanner.nextInt();
	    SavingsAccount savingsAccount = null;
	    try {
	      savingsAccount = savingsAccountService.getAccountById(accountNumber);
	    }
	    catch (AccountNotFoundException e1)
	    {
	      e1.printStackTrace();
	    }
	    System.out.println("Select a field to update \n 1. Update Name \n 2. Update Salaried? \n 3. Update Name and Salaried? ");
	    int input = scanner.nextInt();
	    
	    switch (input)
	    {
	    case 1: 
	      System.out.println("Enter a new name: ");
	      String newName = scanner.nextLine();
	      newName = scanner.nextLine();
	      savingsAccount.getBankAccount().setAccountHolderName(newName);
	      try {
	        savingsAccount = savingsAccountService.updateAccount(savingsAccount);
	        System.out.println(savingsAccount);
	      }
	      catch (AccountNotFoundException e) {
	        e.printStackTrace();
	      }
	    break;
	    
	    case 2: 
	      System.out.println("Change Salaried?(y/n): ");
	      boolean salary = !scanner.next().equalsIgnoreCase("n");
	      savingsAccount.setSalary(salary);
	      try {
	        savingsAccount = savingsAccountService.updateAccount(savingsAccount);
	        System.out.println(savingsAccount);
	      }
	      catch (AccountNotFoundException e) {
	        e.printStackTrace();
	      }
	    break;
	    
	    case 3: 
	      System.out.println("Enter a new name: ");
	      newName = scanner.nextLine();
	      newName = scanner.nextLine();
	      System.out.println("Change Salaried?(y/n): ");
	      salary = !scanner.next().equalsIgnoreCase("n");
	      savingsAccount.getBankAccount().setAccountHolderName(newName);
	      savingsAccount.setSalary(salary);
	      try {
	        savingsAccount = savingsAccountService.updateAccount(savingsAccount);
	        System.out.println(savingsAccount);
	      }
	      catch (AccountNotFoundException e) {
	        e.printStackTrace();
	      }
	      break;
	    }
	  
	}
		
	private void closeAccount() {
		System.out.println("Select Account to close \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Enter Savings Account Number: ");
			int accountNumber = scanner.nextInt();
			SavingsAccount deleteAccount = savingsAccountService.deleteAccount(accountNumber);
			System.out.println("Account for account number "+accountNumber +" is successfully closed.");
			break;

		case 2:
			System.out.println("Enter Savings Account Number: ");
			int currentAccountNumber = scanner.nextInt();
			try {
				CurrentAccount deleteCurrentAccount = currentAccountService.deleteAccount(currentAccountNumber);
				System.out.println("Account for account number "+currentAccountNumber +" is successfully closed.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		
		
	}
	
	private void searchAccount() {
		System.out.println("Select Account to close \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println(" Search Account Number By: \n 1.Name\n 2.Account number\n 3.Balance Range  ");
			int userChoice = scanner.nextInt();
			switch (userChoice) {
			case 1:
				System.out.println("Enter name to be search associated with the account: ");
				String accountHolderName = scanner.nextLine();
				try {
					accountHolderName = scanner.nextLine();
					SavingsAccount searchAccountByname = savingsAccountService.getAccountByName(accountHolderName);
					System.out.println(searchAccountByname);
				} catch (AccountNotFoundException e1) {
					e1.printStackTrace();
				}
				break;

			case 2:
				System.out.println("Enter the account number to be search: ");
				int accNumber = scanner.nextInt();
				
				try {
					SavingsAccount searchAccount = savingsAccountService.getAccountById(accNumber);
					System.out.println(searchAccount);
				} catch (AccountNotFoundException e) {
					e.printStackTrace();
				}
				break;
			
			case 3:
				List<SavingsAccount> searchAccountByBalance;
				System.out.println("Enter the Minimum Balance Range: ");
				int minBalance = scanner.nextInt();
				System.out.println("Enter the Maximum Balance Range: ");
				int maxBalance = scanner.nextInt();
				try {
					searchAccountByBalance = savingsAccountService.getAccountsByBalance(minBalance, maxBalance);
					for (SavingsAccount savingsAccount : searchAccountByBalance) {
						System.out.println(savingsAccount);
					}
				} catch (AccountNotFoundException e) {
					e.printStackTrace();
				}
				break;
			}	
			break;
		
		case 2:
			System.out.println(" Search Account Number By: \n 1.Name\n 2.Account number\n 3.Balance Range  ");
			int currentUserChoice = scanner.nextInt();
			switch (currentUserChoice) {
			case 1:
				System.out.println("Enter name to be search associated with the account: ");
				String accountHolderName = scanner.nextLine();
			try {
				accountHolderName = scanner.nextLine();
				CurrentAccount searchAccountByname = currentAccountService.getAccountByName(accountHolderName);
				System.out.println(searchAccountByname);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e1) {
				e1.printStackTrace();
			}
			break;

			case 2:
				System.out.println("Enter the account number to be search: ");
				int accNumber = scanner.nextInt();
			
			try {
				CurrentAccount searchAccount = currentAccountService.getAccountById(accNumber);
				System.out.println(searchAccount);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		
		case 3:
			List<CurrentAccount> searchAccountByBalance;
			System.out.println("Enter the Minimum Balance Range: ");
			int minBalance = scanner.nextInt();
			System.out.println("Enter the Maximum Balance Range: ");
			int maxBalance = scanner.nextInt();
			try {
				searchAccountByBalance = currentAccountService.getAccountsByBalance(minBalance, maxBalance);
				for (CurrentAccount currentAccount : searchAccountByBalance) {
					System.out.println(currentAccount);
				}
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		
		    }
		}
	}

	private void withdraw() {
		System.out.println("Select Account to withdraw amount \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Enter Account Number: ");
			int accountNumber = scanner.nextInt();
			System.out.println("Enter Amount: ");
			double amount = scanner.nextDouble();
			SavingsAccount savingsAccount = null;
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
				savingsAccountService.withdraw(savingsAccount, amount);
			} catch (AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Enter Account Number: ");
			int currentAccountNumber = scanner.nextInt();
			System.out.println("Enter Amount: ");
			double amountToWithdraw = scanner.nextDouble();
			CurrentAccount currentAccount = null;
			try {
				currentAccount = currentAccountService.getAccountById(currentAccountNumber);
				currentAccountService.withdraw(currentAccount, amountToWithdraw);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}
		
	}

	private void deposit() {
		System.out.println("Select Account to withdraw amount \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Enter Account Number: ");
			int accountNumber = scanner.nextInt();
			System.out.println("Enter Amount: ");
			double amount = scanner.nextDouble();
			SavingsAccount savingsAccount = null;
				
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
				savingsAccountService.deposit(savingsAccount, amount);
			} catch (AccountNotFoundException e2) {
				e2.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Enter Account Number: ");
			int currentAccountNumber = scanner.nextInt();
			System.out.println("Enter Amount: ");
			double amountToDeposit = scanner.nextDouble();
			CurrentAccount currentAccount = null;
			try {
				currentAccount = currentAccountService.getAccountById(currentAccountNumber);
				currentAccountService.deposit(currentAccount, amountToDeposit);
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
			}
			break;
		}
		
	}

	private void fundTransfer() {
		System.out.println("Select Account to withdraw amount \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Enter Account Sender's Number: ");
			int senderAccountNumber = scanner.nextInt();
			System.out.println("Enter Account Receiver's Number: ");
			int receiverAccountNumber = scanner.nextInt();
			System.out.println("Enter Amount: ");
			double amount = scanner.nextDouble();
			
			try {
				SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
				SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
				savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Enter Account Sender's Number: ");
			int sendersAccountNumber = scanner.nextInt();
			System.out.println("Enter Account Receiver's Number: ");
			int receiversAccountNumber = scanner.nextInt();
			System.out.println("Enter Amount: ");
			double amountToTransfer = scanner.nextDouble();
			
			try {
				CurrentAccount senderSavingsAccount = currentAccountService.getAccountById(sendersAccountNumber);
				CurrentAccount receiverSavingsAccount = currentAccountService.getAccountById(receiversAccountNumber);
				currentAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amountToTransfer);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
	}

	private void showCurrentBalance() {
		System.out.println("Enter the account number: ");
		int accountNumber = scanner.nextInt();
		double currentBalance;
		try {
			currentBalance = savingsAccountService.showCurrentBalance(accountNumber);
			System.out.println(currentBalance);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	private void showAllAccounts() {
		System.out.println("Select Account to Show all accounts \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			List<SavingsAccount> savingsAccounts;
			savingsAccounts = savingsAccountService.getAllSavingsAccount();
			for (SavingsAccount savingsAccount : savingsAccounts) {
				System.out.println(savingsAccount);
			}
			break;

		case 2:
			List<CurrentAccount> currentAccounts;
			try {
				currentAccounts = currentAccountService.getAllCurrentAccount();
				for (CurrentAccount currentAccount : currentAccounts) {
					System.out.println(currentAccount);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		
	}
	
	private void sortAccounts() {
		System.out.println("Select Account to Show all accounts \n 1.Savings account \n 2.Current account  ");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			List<SavingsAccount> sortAllAccounts;
			System.out.println("Sort accounts by \n 1.Account Number \n 2.Name \n 3.Account Balance");
			int userChoice = scanner.nextInt();
			switch (userChoice) {
			case 1:
				sortAllAccounts = savingsAccountService.sortAllSavingsAccount(userChoice);
				System.out.println(sortAllAccounts);
				break;

			case 2:
				sortAllAccounts = savingsAccountService.sortAllSavingsAccount(userChoice);
				System.out.println(sortAllAccounts);
				break;

			case 3:
				sortAllAccounts = savingsAccountService.sortAllSavingsAccount(userChoice);
				System.out.println(sortAllAccounts);
				break;
			}
			break;

		case 2:
			List<CurrentAccount> sortAllCurrentAccounts;
			System.out.println("Sort accounts by \n 1.Account Number \n 2.Name \n 3.Account Balance");
			int currentUserChoice = scanner.nextInt();
			switch (currentUserChoice) {
			case 1:
				try {
					sortAllCurrentAccounts = currentAccountService.sortAllCurrentAccount(currentUserChoice);
					System.out.println(sortAllCurrentAccounts);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				try {
					sortAllCurrentAccounts = currentAccountService.sortAllCurrentAccount(currentUserChoice);
					System.out.println(sortAllCurrentAccounts);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				try {
					sortAllCurrentAccounts = currentAccountService.sortAllCurrentAccount(currentUserChoice);
					System.out.println(sortAllCurrentAccounts);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			break;
		}
		
	}

	private void sortMenu(String sortWay) {
		do {
			System.out.println("+++++Ways of Sorting+++++++");
			System.out.println("1. Account Number");
			System.out.println("2. Account Holder Name");
			System.out.println("3. Account Balance");
			System.out.println("4. Exit from Sorting");
			
			int choice = scanner.nextInt();
			
		}while(true);
		
	}

}



