package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	// 19:00 (7pm)	23:00 (11pm)
	private Pizza myPizza;
	private Pizza notGonnaWork;
	private LocalTime order = LocalTime.parse("20:30:00");
	private LocalTime deliver = LocalTime.parse("20:45:00");
	
	
	// Testing orderTime of 8:30pm and deliveryTime of 8:45pm which are both valid
	@Before
	public void createMargherita() throws PizzaException {
		myPizza = new MargheritaPizza(3, order, deliver);
	}
	
	// Quantity '3' should be valid
	@Test
	public void quantityTestOne() throws PizzaException {
		assertEquals(myPizza.getQuantity(), 3);
	}
	
	// Quantity '10' should be valid
	@Test
	public void quantityTestTwo() throws PizzaException {
		Pizza thisPizza = new MargheritaPizza(10, LocalTime.parse("19:15:00"), LocalTime.parse("20:00:00"));
		assertEquals(thisPizza.getQuantity(), 10);
	}
	
	// Quantity '1' should be valid
	@Test
	public void quantityTestThree() throws PizzaException {
		Pizza thisPizza = new MargheritaPizza(1, LocalTime.parse("19:15:00"), LocalTime.parse("20:00:00"));
		assertEquals(thisPizza.getQuantity(), 1);
	}
	
	// Quantity Negative
	@Test (expected = PizzaException.class)
	public void quantityNegative() throws PizzaException {
		notGonnaWork = new MargheritaPizza(-1, order, deliver);
	}
	
	// Quantity Above 10 - test one
	@Test (expected = PizzaException.class)
	public void quantityAbove10() throws PizzaException {
		notGonnaWork = new MargheritaPizza(11, order, deliver);
	}
	
	// Quantity Above 10 - test two
	@Test (expected = PizzaException.class)
	public void quantityAbove10T2() throws PizzaException {
		notGonnaWork = new MargheritaPizza(20, order, deliver);
	}
	
	//	///	///	///	///	///	//	//	//	///	////	/////	////	////	////	/////	/////	/////	////	/////	///
	
	// Order time 6:30pm not allowed
	@Test (expected = PizzaException.class)
	public void orderTimeBefore7pmTestOne() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("18:30:00"), LocalTime.parse("19:20:00"));
	}
	
	// Order time 6:59pm still not allowed
	@Test (expected = PizzaException.class)
	public void orderTimeBefore7pmTestTwo() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("18:59:00"), LocalTime.parse("19:20:00"));
	}
	
	// Order time 11:30am not allowed
	@Test (expected = PizzaException.class)
	public void orderTimeBefore7pmTestThree() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("11:30:00"), LocalTime.parse("12:20:00"));
	}
	
	// Order time 11:30pm not allowed
	@Test (expected = PizzaException.class)
	public void orderTimeAfter11pmTestOne() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("23:30:00"), LocalTime.parse("00:20:00"));
		
	}
	
	// Order time 11:01pm not allowed
	@Test (expected = PizzaException.class)
	public void orderTimeAfter11pmTestTwo() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("23:01:00"), LocalTime.parse("00:00:00"));
		
	}
	
	// Store closes at 11pm, so ordering at that time is not allowed
	@Test (expected = PizzaException.class)
	public void orderTimeAt11pm() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("23:00:00"), LocalTime.parse("23:50:00"));
	}
	
	// Delivery time can't be lower than Order time - test one
	@Test (expected = PizzaException.class)
	public void deliveryTimeTestOne() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("22:30:00"), LocalTime.parse("22:28:00"));
	}
	
	// Delivery time can't be lower than Order time - test two
	@Test (expected = PizzaException.class)
	public void deliveryTimeTestTwo() throws PizzaException {
		notGonnaWork = new MargheritaPizza(8, LocalTime.parse("19:30:00"), LocalTime.parse("22:28:00"));
	}
}
