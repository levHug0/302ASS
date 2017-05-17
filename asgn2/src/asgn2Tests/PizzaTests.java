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
	// TO DO
	
	/* Margerita
	 * 			Tests
	 * 					Below
	 */
	private Pizza myPizza;
	
	@Before
	public void createMargherita() throws PizzaException {
		LocalTime time01 = LocalTime.parse("20:30");
		LocalTime time02 = LocalTime.parse("20:45");
		myPizza = new MargheritaPizza(3, time01, time02);
	}
	
	@Test
	public void testMe() throws PizzaException {
		assertEquals(myPizza.getQuantity(), 3);
	}
	
}
