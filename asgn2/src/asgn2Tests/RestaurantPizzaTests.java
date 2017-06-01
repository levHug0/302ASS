package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

import static org.junit.Assert.*;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Raj Rosello
 *
 */
public class RestaurantPizzaTests {
	
	private PizzaRestaurant pz;
	
	@Before
	public void createPizzaRestaurant() {
		pz = new PizzaRestaurant();
	}
	
	@Test
	public void sizeIsZero() {
		assertEquals(pz.getNumPizzaOrders(), 0);
	}
	
	@Test
	public void processLog() throws CustomerException, PizzaException, LogHandlerException {
		assertTrue(pz.processLog("20170101.txt"));
	}
	
	@Test
	public void test2() throws CustomerException, PizzaException, LogHandlerException {
		pz.processLog("20170101.txt");
		assertEquals(pz.getNumPizzaOrders(), 3);
	}
}
