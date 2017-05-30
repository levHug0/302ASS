package asgn2Tests;

import asgn2Restaurant.LogHandler;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	// TO DO	
	@Test
	public void test1() throws PizzaException, LogHandlerException {
		ArrayList<Pizza> pizzaList = null;
		pizzaList = LogHandler.populatePizzaDataset("20170101.txt");
		assertEquals(pizzaList.get(0).getQuantity(), 2);
	}
	
	
}
