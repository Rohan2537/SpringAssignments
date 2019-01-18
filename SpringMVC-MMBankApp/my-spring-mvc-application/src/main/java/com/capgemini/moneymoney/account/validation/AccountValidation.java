package com.capgemini.moneymoney.account.validation;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.capgemini.moneymoney.account.SavingsAccount;

@Component
@Aspect
public class AccountValidation {
	private Logger logger =Logger.getLogger(AccountValidation.class.getName());
	private AccountValidation validation;
	
	@Around("execution(* com.capgemini.moneymoney.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void validateDeposit(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Function name is:"+pjp.getSignature());
		Object[] params = pjp.getArgs();
		SavingsAccount account = (SavingsAccount) params[0];
		double amountToDeposit = (double) params[1];
		if(amountToDeposit>0) {
		Object actual = pjp.proceed();
		logger.info(" Amount "+amountToDeposit +" is deposited into account "+account.getBankAccount().getAccountNumber() +" Successfully !!!");
		}
		else logger.info(" Invalid Input Amount !!!");
	}
	
	@Around("execution(* com.capgemini.moneymoney.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void validateWithdraw(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Function name is:"+pjp.getSignature());
		Object[] params = pjp.getArgs();
		SavingsAccount account = (SavingsAccount) params[0];
		double amountToWithdraw = (double) params[1];
		double currentBalance = account.getBankAccount().getAccountBalance();
		if(amountToWithdraw>0 && currentBalance>=amountToWithdraw ) {
		Object actual = pjp.proceed();
		logger.info(" Amount "+amountToWithdraw +"is withdrawn from account "+account.getBankAccount().getAccountNumber() +" Successfully !!!");
		}
		else logger.info(" Invalid Input or Insufficient Funds! !!!");
	}
	
	@Around("execution(* com.capgemini.moneymoney.account.service.SavingsAccountServiceImpl.fundTransfer(..))")
	public void validateFundTransfer(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Function name is:"+pjp.getSignature());
		Object[] params = pjp.getArgs();
		SavingsAccount  senderaccount = (SavingsAccount) params[0];
		SavingsAccount  receiverAccount = (SavingsAccount) params[1];
		double amountToTransfer = (double) params[2];
		if(amountToTransfer>0) {
			Object actual = pjp.proceed();
			logger.info(" Amount "+amountToTransfer+ " is transferred from account "+senderaccount.getBankAccount().getAccountNumber()+ " to account "+receiverAccount.getBankAccount().getAccountNumber() +" Successfully !!!");
		}
		else logger.info("Invalid Input !!!");
		
	}

}
