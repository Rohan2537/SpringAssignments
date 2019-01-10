package com.capgemini.calculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(100, 200);
    }
}
