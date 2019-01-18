package com.capgemini.account.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.service.SavingsAccountService;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

@Controller
public class AccountController {
	@Autowired
	private SavingsAccountService savingsAccountService ;
	private List<SavingsAccount> savingsAccounts;
	private SavingsAccount account;
	private boolean toSortIn=false;
	
	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/newSA")
	public String getCreateForm() {
		return "AddNewSavingsAccount";
	}

	@RequestMapping("/createAccount")
	public String createAccount(@RequestParam("accHolderName")String accountHolderName,
			@RequestParam("accountBalance")double accountBalance,
			@RequestParam("rdSalary")boolean salary) {
		account = savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/updateAccount")
	public String searchAccount() {
		return "updateAccountForm";
	}

	@RequestMapping("/accountToUpdate")
	public String getAccount(@RequestParam("accountNumber") int accountNumber, Model model) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		 model.addAttribute("account", account);
		return "AccountDetails";
	}
	
	@RequestMapping("/updateAcc")
	public String updateAccount(@RequestParam("accountNumber") int accountNumber, @RequestParam("newName") String accountHolderName,
			@RequestParam("accountBalance") double accountBalance,
			@RequestParam("accountType") String accountType,@RequestParam("newSalaried") String salaried, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		boolean salariedOrNot = salaried.equalsIgnoreCase("yes")?true:false;
		SavingsAccount savingAccountToUpdate = new SavingsAccount(accountNumber, accountHolderName,accountBalance, salariedOrNot);
		savingAccountToUpdate = savingsAccountService.updateAccount(savingAccountToUpdate);
		model.addAttribute("account", savingAccountToUpdate);
		return "AccountDetails";
	}
	
	@RequestMapping("/searchAccount")
	public String getSearchAccountForm() {
		return "searchBy";
	}
	
	@RequestMapping("/searchByAccountId")
	public String searchByAccountId() {
		return "searchById";
	}
	
	@RequestMapping("/searchAccById")
	public String searchById(@RequestParam("accountNumber") int accountNumber,Model model) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "AccountDetails";
	}
	
	
	@RequestMapping("/searchByName")
	public String searchByAccountName() {
		return "searchByName";
	}
	
	@RequestMapping("/searchByAccountName")
	public String searchByAccountName(@RequestParam("accountHolderName") String accountHolderName, Model model) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountByName(accountHolderName);
		model.addAttribute("account", account);
		return "AccountDetails";
	}
	
	
	@RequestMapping("/searchByBalanceRange")
	public String searchByBalanceRange() {
		return "searchByBalanceRanges";
	}
	
	@RequestMapping("/searchAccountByBalanceRange")
	public String searchAccountByBalanceRange(@RequestParam("minimumBalance") double minimumBalance, @RequestParam("maximumBalance") int maximumBalance,
			Model model) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		List<SavingsAccount> accounts = savingsAccountService.getAccountsByBalance(minimumBalance, maximumBalance);
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}
	
	
	@RequestMapping("/currentBalance")
	public String getCurrentBalanceForm() {
		return "currentBalance";
	}
	
	@RequestMapping("/getCurrentBalance")
	public String currentAccountBalance(@RequestParam("accountNumber")int accountNumber) throws AccountNotFoundException {
		account =  savingsAccountService.getAccountById(accountNumber);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/closeAccount")
	public String getCloseAccountForm() {
		return "closeAccount";
	}
	
	@RequestMapping("/closeAcc")
	public String closeAccount(@RequestParam("accountNumber")int accountNumber) {
		account = savingsAccountService.deleteAccount(accountNumber);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/deposit")
	public String getDepositForm() {
		return "deposit";
	}
	
	@RequestMapping("/depositAmount")
	public String depositAmount(@RequestParam("accountNumber")int accountNumber,
			@RequestParam("amountToDeposit")double amountToDeposit) throws AccountNotFoundException {
		account=savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.deposit(account, amountToDeposit);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/withdraw")
	public String getWithdrawForm() {
		return "withdraw";
	}
	
	@RequestMapping("/withdrawAmount")
	public String withdrawAmount(@RequestParam("accountNumber")int accountNumber,
			@RequestParam("amountToWithdraw")double amountToWithdraw) throws AccountNotFoundException {
		account=savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.withdraw(account, amountToWithdraw);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/transferAmount")
	public String getFundTransferForm() {
		return "fundTransfer";
	}
	
	@RequestMapping("/transfer")
	public String fundTransfer(@RequestParam("senderAccountNumber")int senderAccountNumber,
			@RequestParam("receiverAccountNumber")int receiverAccountNumber,
			@RequestParam("amountToTransfer")double amountToTransfer) throws AccountNotFoundException {
		SavingsAccount senderSavingsAccount = null;
		SavingsAccount receiverSavingsAccount = null;
		senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
		receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
		savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amountToTransfer);
	
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/getAllAccounts")
	public String getAllAccounts(Model model) {
		savingsAccounts = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts",savingsAccounts);
		return "AccountDetails";
	}
	
	@RequestMapping("/sortByName")
	public String sortByName(Model model) throws ClassNotFoundException, SQLException {
		
		List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		toSortIn = !toSortIn;
		final int sort = toSortIn == false ? 1 : -1;									
		Collections.sort(accounts, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
				return sort* (accountOne.getBankAccount().getAccountHolderName().compareToIgnoreCase(accountTwo.getBankAccount().getAccountHolderName()));
			}
		});
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}
	
	
	@RequestMapping("/sortAccountsById")
	public String sortByAccountId(Model model) throws ClassNotFoundException, SQLException {
		
		List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		toSortIn = !toSortIn;
		final int sort = toSortIn == false ? 1 : -1;									
		Collections.sort(accounts, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
				if(accountOne.getBankAccount().getAccountNumber() >accountTwo.getBankAccount().getAccountNumber())
					return -1*sort;
				else if(accountOne.getBankAccount().getAccountNumber() < accountTwo.getBankAccount().getAccountNumber())
					return 1*sort;
				else
					return 0;
			}
		});
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}
	
	@RequestMapping("/sortAccountsBySalary")
	public String sortAccountByIsSalaried(Model model) throws ClassNotFoundException, SQLException {
		
		List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		toSortIn = !toSortIn;
		final int sort = toSortIn == false ? 1 : -1;									
		Collections.sort(accounts, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
				if(accountOne.isSalary() == accountTwo.isSalary())
					return 0*sort;
				else
					return sort*-1;
			}
		});
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}
}
