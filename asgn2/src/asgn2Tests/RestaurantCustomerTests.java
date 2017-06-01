package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Exceptions.CustomerException;
import asgn2Customers.Customer;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Levinard Hugo (Person A)
 */
public class RestaurantCustomerTests {
	private PizzaRestaurant pr;
	
	// Create PizzaRestaurant, and testing processlog for 20170101.txt 
	@Before
	public void createPizzaRestaurant() throws PizzaException, CustomerException, LogHandlerException {
		pr = new PizzaRestaurant(); 
		pr.processLog("20170101.txt");
	}
	
	// Test constructor, customers list should be empty, since process log won't be called
	@Test
	public void testConstructor() {
		PizzaRestaurant dr = new PizzaRestaurant();
		assertEquals(dr.getNumCustomerOrders(), 0);
	}
	// Process Log for 20170102.txt
	@Test
	public void processLogtest2() throws PizzaException, CustomerException, LogHandlerException {
		assertTrue(pr.processLog("20170102.txt"));
	}
	
	// Process Log for 20170103.txt
	@Test
	public void processLogtest3() throws PizzaException, CustomerException, LogHandlerException {
		assertTrue(pr.processLog("20170103.txt"));
	}
	
	// getNumCustomerOrders() test
	@Test
	public void numOrdersT()  {
		assertEquals(pr.getNumCustomerOrders(), 3);
	}
	
	// getNumCustomerOrders() should be the same as getNumPizzaOrders()
	@Test
	public void numOrdersT2() {
		assertEquals(pr.getNumCustomerOrders(), pr.getNumPizzaOrders());
	}
	
	// getTotalDeliveryDistance() for file 20170101.txt should be 15, becase distance for line 1 is 10, for line 2 its 5
	// line 3 is 0, so in total its 15
	@Test
	public void totalDelDis() {
		assertEquals(pr.getTotalDeliveryDistance(), 15, 0.00001);
	}
	
	// resetDetails the list should now be empty
	@Test
	public void resetDeets() {
		pr.resetDetails();
		assertEquals(pr.getNumCustomerOrders(), 0);
	}
	
	// getCustomerByIndex indirectly testing
	@Test
	public void getCustomerByIndex() throws CustomerException {
		assertEquals(pr.getCustomerByIndex(0).getName(), "Casey Jones");
	}
	
	// getCustomerByIndex indirectly testing2
	@Test
	public void getCustomerByIndex2() throws CustomerException {
		assertEquals(pr.getCustomerByIndex(1).getName(), "April O'Neal");
	}
	
	// getCustomerByIndex indirectly testing3
	@Test
	public void getCustomerByIndex3() throws CustomerException {
		assertEquals(pr.getCustomerByIndex(2).getName(), "Oroku Saki");
	}
	
	// Negative getCustomerByIndex()
	@Test (expected = CustomerException.class)
	public void negativeIndex() throws CustomerException {
		Customer myFavouriteCustomerThatWillNotWork = pr.getCustomerByIndex(-1);
	}
	
	// Negative getCustomerByIndex() too far
	@Test (expected = CustomerException.class)
	public void negativeIndexTF() throws CustomerException {
		Customer myFavouriteCustomerThatWillNotWork = pr.getCustomerByIndex(-100);
	}
	
	// Out of bounds getCustomerByIndex()
	@Test (expected = CustomerException.class)
	public void outOfBoundsIndex() throws CustomerException {
		// pr size is only 3, so if a number above 3 is specified it shouldn't work
		Customer myFavouriteCustomerThatWillNotWork = pr.getCustomerByIndex(4);
	}
	
	// Out of bounds getCustomerByIndex() soooo out of bound
	@Test (expected = CustomerException.class)
	public void outOfBoundsIndexSoOutOfBound() throws CustomerException {
		// pr size is only 3, so if a number above 3 is specified it shouldn't work
		Customer myFavouriteCustomerThatWillNotWork = pr.getCustomerByIndex(69);
	}
}
