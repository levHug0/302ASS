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
* @author Raj Rosello (Person B)
* 
*/
public class LogHandlerPizzaTests {
	private ArrayList<Pizza> list;
	private ArrayList<Pizza> notGonnaWork;
	
	// Creating the ArrayList using populatePizzaDataset method, as well as indirectly testing createPizza method
	// There are three lines in the textfile, therefore, the size of the ArrayList should be 3
	// First line is a Vegetarian Pizza, Quantity of 2 
	// Second line is a Margherita Pizza, Quantity of 1
	// Third line is a MeatLovers Pizza, Quantity of 3
	@Before
	public void createList() throws PizzaException, LogHandlerException {
		list = LogHandler.populatePizzaDataset("20170101.txt");
	}
	
	// Test Line 1 is a Vegetarian Pizza
	@Test
	public void checkTextFileLineOne() throws PizzaException, LogHandlerException {
		assertEquals(list.get(0).getPizzaType(), "Vegetarian");
	}
	
	// Test Line 2 is a Margherita Pizza
	@Test
	public void checkTextFileLineTwo() throws PizzaException, LogHandlerException {
		assertEquals(list.get(1).getPizzaType(), "Margherita");
	}
	
	// Test Line 3 is a MeatLovers Pizza
	@Test
	public void checkTextFileLineThree() throws PizzaException, LogHandlerException {
		assertEquals(list.get(2).getPizzaType(), "Meat Lovers");
	}
	
	// Quantity of line 1
	@Test
	public void quantityLine1() throws PizzaException, LogHandlerException {
		assertEquals(list.get(0).getQuantity(), 2);
	}
		
	// Quantity of line 2
	@Test
	public void quantityLineTwo() throws PizzaException, LogHandlerException {
		assertEquals(list.get(1).getQuantity(), 1);
	}
		
	// Quantity of line 3
	@Test
	public void quantityLineThree() throws PizzaException, LogHandlerException {
		assertEquals(list.get(2).getQuantity(), 3);
	}
	
	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//	//
	
	// Exception if the file doesnt exist
	@Test (expected = LogHandlerException.class)
	public void fileDoesntExist() throws PizzaException, LogHandlerException {
		 notGonnaWork = LogHandler.populatePizzaDataset("Idontexist.txt");
	}
	
	// Incorrect pizza code
	@Test (expected = LogHandlerException.class)
	public void incorrectPizzaCode() throws PizzaException, LogHandlerException {
		notGonnaWork = LogHandler.populatePizzaDataset("incorrectPizzaCode.txt");
	}
	
	// Incorrect quantity type
	@Test (expected = LogHandlerException.class)
	public void negativeQuantity() throws PizzaException, LogHandlerException {
		notGonnaWork = LogHandler.populatePizzaDataset("quantityNegative.txt");
	}
	
	// incorrect order time format
	@Test (expected = LogHandlerException.class)
	public void incorrectOrderTimeFormat() throws PizzaException, LogHandlerException {
		notGonnaWork = LogHandler.populatePizzaDataset("incorrectOrderTimeFormat.txt");
	}
	
	// incorrect delivery time format
	@Test (expected = LogHandlerException.class)
	public void incorrectDeliveryTimeFormat() throws PizzaException, LogHandlerException {
		notGonnaWork = LogHandler.populatePizzaDataset("incorrectDeliveryTimeFormat.txt");
	}
}
