package asgn2Tests;

import asgn2Restaurant.LogHandler;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	// TO DO	
	private LogHandler log;
	
	
	@Before
	public void createLog() {
		log = new LogHandler();
	}
	
}
