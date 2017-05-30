package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.CustomerException;
import asgn2Customers.Customer;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Person A
 */
public class LogHandlerCustomerTests {
	// TO DO
	
	@Test
	public void test1() throws CustomerException, LogHandlerException {
		ArrayList<Customer> customerList = null;
		customerList = LogHandler.populateCustomerDataset("20170101.txt");
		assertEquals(customerList.get(0).getName(), "Casey Jones");
	}
	
	
}
