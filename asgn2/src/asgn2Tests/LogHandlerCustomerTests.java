package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.CustomerException;
import asgn2Customers.Customer;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Levinard Hugo (Person A)
 */
public class LogHandlerCustomerTests {
	private ArrayList<Customer> custom;
	private ArrayList<Customer> notGonnaWork;
	
	@Before
	public void createArrayList() throws CustomerException, LogHandlerException {
		custom = LogHandler.populateCustomerDataset("20170101.txt");
	}
	
	// Check line one mobile number
	@Test
	public void checkMobileNumber() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(0).getMobileNumber(), "0123456789");
	}
	
	// Check line Two mobile number
	@Test
	public void checkMobileNumberT2() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(1).getMobileNumber(), "0987654321");
	}
		
	// Check line three mobile number
	@Test
	public void checkMobileNumbert3() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(2).getMobileNumber(), "0111222333");
	}
	
	// Line one Driver Customer
	@Test
	public void driverDeliveryLine1() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(0).getCustomerType(), "Driver Delivery");
	}
	
	// Line 2 Drone Customer
	@Test
	public void driverDeliveryLine2() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(1).getCustomerType(), "Drone Delivery");
	}
		
	// Line 3 Pick Up
	@Test
	public void driverDeliveryLine3() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(2).getCustomerType(), "Pick Up");
	}
	
	// Line one name
	@Test
	public void nameLine1() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(0).getName(), "Casey Jones");
	}
		
	// Line 2 name
	@Test
	public void nameLine2() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(1).getName(), "April O'Neal");
	}
			
	// Line 3 name
	@Test
	public void nameLine3() throws CustomerException, LogHandlerException {
		assertEquals(custom.get(2).getName(), "Oroku Saki");
	}
	
	//	//	//	//	//	//	/	/	//	/	///	/	//	/	//	//	//	//	//	//	/	/	//	/	///	/	//	/
	
	// Incorrect mobile 
	@Test (expected = LogHandlerException.class)
	public void incorrectMobile() throws CustomerException, LogHandlerException {
		notGonnaWork = LogHandler.populateCustomerDataset("Customer_incorrectMobile.txt");
	}
	
	// Incorrect customer type
	@Test (expected = LogHandlerException.class)
	public void incorrectCustomerType() throws CustomerException, LogHandlerException {
		notGonnaWork = LogHandler.populateCustomerDataset("Customer_incorrectCustomerType.txt");
	}
	
	// Incorrect customer locationX
	@Test (expected = LogHandlerException.class)
	public void incorrectCustomerLocationX() throws CustomerException, LogHandlerException {
		notGonnaWork = LogHandler.populateCustomerDataset("Customer_incorrectLocationX.txt");
	}
	
	// Incorrect customer locationY
	@Test (expected = LogHandlerException.class)
	public void incorrectCustomerLocationY() throws CustomerException, LogHandlerException {
		notGonnaWork = LogHandler.populateCustomerDataset("Customer_incorrectLocationY.txt");
	}
	
	// Something is wrong in line 3, so the rest of the arraylist of customer shouldn't populate
	@Test (expected = LogHandlerException.class) 
	public void somethingWrongInLine3() throws CustomerException, LogHandlerException {
		notGonnaWork = LogHandler.populateCustomerDataset("Customer_incorrectLine3Location.txt");
	}
	
	// Should throw a exception if locationX is more than 10 blocks
	@Test (expected = LogHandlerException.class)
	public void throwCustomException() throws CustomerException, LogHandlerException {
		notGonnaWork = LogHandler.populateCustomerDataset("Customer_customerException_forLocationX.txt");
	}
	
	// Using the equals method to test equality
	@Test
	public void useEqualsT1() throws CustomerException, LogHandlerException{
		ArrayList<Customer> test1 = LogHandler.populateCustomerDataset("20170101.txt");
		ArrayList<Customer> test2 = LogHandler.populateCustomerDataset("20170101.txt");
		assertTrue(test1.get(0).equals(test2.get(0)));
	}
	
	// Using the equals method to test equality test 2
	@Test
	public void useEqualsT2() throws CustomerException, LogHandlerException{
		ArrayList<Customer> test1 = LogHandler.populateCustomerDataset("20170101.txt");
		ArrayList<Customer> test2 = LogHandler.populateCustomerDataset("20170101.txt");
		assertTrue(test1.get(1).equals(test2.get(1)));
	}
	
	// Using the equals method to test equality test 3
	@Test
	public void useEqualsT3() throws CustomerException, LogHandlerException{
		ArrayList<Customer> test1 = LogHandler.populateCustomerDataset("20170101.txt");
		ArrayList<Customer> test2 = LogHandler.populateCustomerDataset("20170101.txt");
		assertTrue(test1.get(2).equals(test2.get(2)));
	}
	
}
