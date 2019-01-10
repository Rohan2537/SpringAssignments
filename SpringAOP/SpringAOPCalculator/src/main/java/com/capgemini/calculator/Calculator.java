package com.capgemini.calculator;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

	public Integer add(int numberOne,int numberTwo) {
		//System.out.println(numberOne+numberTwo);
		return numberOne+numberTwo;
	}
}
