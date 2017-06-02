package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;

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
	public void createPizzaRestaurant() throws PizzaException, CustomerException, LogHandlerException {
		pz = new PizzaRestaurant();
		pz.processLog("20170101.txt");
	}
	
	// pizza should be empty since it's not yet populated with processlog method
	@Test
	public void testConstructor(){
		PizzaRestaurant test = new PizzaRestaurant();
		assertEquals(test.getNumPizzaOrders(), 0);
	}
	
	
	//Process Log for 20170102.txt
	@Test
	public void processLogTest() throws PizzaException, CustomerException, LogHandlerException {
		assertTrue(pz.processLog("20170102.txt"));
	}
	
	//Process Log for 20170103.txt
	@Test
	public void processLogTest1() throws PizzaException, CustomerException, LogHandlerException {
		assertTrue(pz.processLog("20170103.txt"));
	}
	
	// getNumPizzaOrders() test
	@Test
	public void pizzaNumOrdersTest(){
		assertEquals(pz.getNumPizzaOrders(), 3);
	}
	
	// getNumPizzaOrders() should have the same return value as getCustomerOrders()
	@Test
	public void pizzaNumAndCustomerNumSameTest(){
		assertEquals(pz.getNumPizzaOrders(),pz.getNumCustomerOrders());
	}

	//Testing getTotalProfitTest() method. Expected to be 36.5
	@Test
	public void getTOtalProfitTest(){
		assertEquals(pz.getTotalProfit(),36,0.5);
	}
	
	// resetDetails the list should clear the current log
	public void resetTest(){
		pz.resetDetails();
		assertEquals(pz.getNumPizzaOrders(),0);
	}
	
	// Indirect testing for getPizzaIndex() 
	public void getPizzaByIndexTest() throws PizzaException {
		assertEquals(pz.getPizzaByIndex(0).getQuantity(),2);
		
	}
	
	//Negative getPizzaByIndex()
	@Test(expected = PizzaException.class)
	public void negativeIndexTest() throws PizzaException{
		Pizza notworking = pz.getPizzaByIndex(-1);
	}
	
	//Negative getPizzaByIndex()
	@Test(expected = PizzaException.class)
	public void negativeIndexTest2() throws PizzaException{
		Pizza notworking = pz.getPizzaByIndex(-100);
	}
	
	//Out of bounds getPizzaByIndex()
	@Test(expected = PizzaException.class)
	public void OutofBoundsIndexTest() throws PizzaException{
		Pizza notworking = pz.getPizzaByIndex(100);
	}
	
	//Out of bounds getPizzaByIndex()
	@Test(expected = PizzaException.class)
	public void OutofBoundsIndexTest2() throws PizzaException{
		Pizza notworking = pz.getPizzaByIndex(4);
	}
	
	
	
	
}
