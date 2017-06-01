package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Raj Rosello 
 * 
 */
public class PizzaFactoryTests {
	
	private Pizza mg;		// margherita
	private Pizza vg;		// vegetarian
	private Pizza ml;	// meat lover
	
	private Pizza notGonnaWork;
	
	private LocalTime order = LocalTime.parse("20:30:00");
	private LocalTime deliver = LocalTime.parse("20:45:00");
	
	
	// Test for creating ALL Pizza types
	@Before
	public void createPizzasUsingPizzaFactory() throws PizzaException {
		mg = PizzaFactory.getPizza("PZM", 2, order, deliver);
		vg = PizzaFactory.getPizza("PZV", 4, order, deliver);
		ml = PizzaFactory.getPizza("PZL", 6, order, deliver);
	}
	
	// Test a pizza code that's not "PZM", "PZV", "PZL"
	@Test (expected = PizzaException.class)
	public void createPizzaUsingWrongPizzaCode() throws PizzaException {
		notGonnaWork = PizzaFactory.getPizza("LOL", 3, order, deliver);
	}
	
	// Test a pizza code that's not "PZM", "PZV", "PZL" test 2
	@Test (expected = PizzaException.class)
	public void createPizzaUsingWrongPizzaCodeT2() throws PizzaException {
		notGonnaWork = PizzaFactory.getPizza("PML", 3, order, deliver);
	}
	
	// pizza code "pzm"	should still work
	@Test
	public void testLowerCasePZM() throws PizzaException {
		notGonnaWork = PizzaFactory.getPizza("pzm", 1, order, deliver);
		assertEquals(notGonnaWork.getPizzaType(), "Margherita");
	}
	
	//negative quantity
	@Test (expected = PizzaException.class)
	public void negativeQuantity() throws PizzaException {
		notGonnaWork = PizzaFactory.getPizza("pzl", -1, order, deliver);
	}
	
}
