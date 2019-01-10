package com.capgemini.calculatoraspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectCalculator {

	private Logger logger= Logger.getLogger(AspectCalculator.class.getName());
	
	  @Before("execution(* com.capgemini.calculator.Calculator.*(..))") public void
	  beforeImpl() { logger.info("Using Before..."); }
	 
	
	@Around("execution(* com.capgemini.calculator.Calculator.*(..))")
	public Object returnImpl(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Function name is:"+pjp.getSignature());
		logger.info("Parameters are:");
		Object[] params = pjp.getArgs();
		for (int i = 0; i < params.length; i++) {
			logger.info("Parameter value at index "+ i +" is " +params[i]);
		}
		Object actual = pjp.proceed();
		logger.info("Returned value is: "+actual);
		return actual;
	}

	
	  @AfterReturning(
	  pointcut="execution(* com.capgemini.calculator.Calculator.add(..))",returning
	  ="retValue") public void returningImpl(Integer retValue) {
	  logger.info("Returned value is: " +retValue); }
	 
	  @AfterThrowing(pointcut = "execution(* com.capgemini.app.service.Calculator.*(..))", throwing = "ex")
		public void exceptionThrow(JoinPoint jp, Throwable ex) {
			logger.info("exception occured");
		}
	  
	  
}
