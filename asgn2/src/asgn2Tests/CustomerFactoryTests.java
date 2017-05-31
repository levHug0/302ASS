package asgn2Tests;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Levinard Hugo (Person A)
 *
 */
public class CustomerFactoryTests {
	private Customer custom;		// Drone delivery
	private int locationX = 3;			// x location
	private int locationY = 4;			// y location
	private String name = "Levi";	// name
	private String mobileNumber = "0123456789";
	
	// Indirectly testing the Drone Delivery and the rest of the parameters 
	@Test
	public void createDroneDelivery() throws CustomerException {
		custom = CustomerFactory.getCustomer("DNC", name, mobileNumber, locationX, locationY);
		assertEquals(custom.getCustomerType(), "Drone Delivery");
	}
	
	// Indirectly testing the Driver Delivery
	@Test
	public void createDriverDelivery() throws CustomerException {
		custom = CustomerFactory.getCustomer("DVC", name, mobileNumber, locationX, locationY);
		assertEquals(custom.getCustomerType(), "Driver Delivery");
	}
	
	// Indirectly testing the Driver Delivery
	@Test
	public void createPickUp() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, mobileNumber, locationX, locationY);
		assertEquals(custom.getCustomerType(), "Pick Up");
	}
	
	// Incorrect CustomerCode test
	@Test (expected = CustomerException.class)
	public void incorrectCustomerCode() throws CustomerException {
		custom = CustomerFactory.getCustomer("LoL", name, mobileNumber, locationX, locationY);
	}
	
	// Incorrect CustomerCode test2
	@Test (expected = CustomerException.class)
	public void incorrectCustomerCodeTest2() throws CustomerException {
		custom = CustomerFactory.getCustomer("WoW", name, mobileNumber, locationX, locationY);
	}
	
	// Incorrect CustomerCode test3
	@Test (expected = CustomerException.class)
	public void incorrectCustomerCodeTest3() throws CustomerException {
		custom = CustomerFactory.getCustomer("WC3", name, mobileNumber, locationX, locationY);
	}
	
	// testing customer code in lower case "puc"
	@Test
	public void lowerCasePickUp() throws CustomerException {
		custom = CustomerFactory.getCustomer("puc", name, mobileNumber, locationX, locationY);
		assertEquals(custom.getCustomerType(), "Pick Up");
	}
	
	// testing customer code in lower case "dnc"
	@Test
	public void lowerCaseDrone() throws CustomerException {
		custom = CustomerFactory.getCustomer("dnc", name, mobileNumber, locationX, locationY);
		assertEquals(custom.getCustomerType(), "Drone Delivery");
	}
	
	// testing customer code in lower case "dnc"
	@Test
	public void lowerCaseDriver() throws CustomerException {
		custom = CustomerFactory.getCustomer("dvc", name, mobileNumber, locationX, locationY);
		assertEquals(custom.getCustomerType(), "Driver Delivery");
	}	
	
	// mobile doesn't start with 0
	@Test (expected = CustomerException.class)
	public void mobileNotZero() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, "2468109819", locationX, locationY);
	}
	
	// mobile less than 10
	@Test (expected = CustomerException.class)
	public void mobileLessThan10() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, "0123456", locationX, locationY);
	}
	
	// mobile more than 10
	@Test (expected = CustomerException.class)
	public void mobileMoreThan10() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, "01234567891", locationX, locationY);
	}
	
	// mobile with a letter 
	@Test (expected = CustomerException.class)
	public void mobileWithaLetter() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, "0123456r78", locationX, locationY);
	}
	
	// mobile with a word
	@Test (expected = CustomerException.class)
	public void mobileWithaWord() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, "012LOL3452", locationX, locationY);
	}
	
	// white space name
	@Test (expected = CustomerException.class)
	public void whiteSpaceName() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", "", mobileNumber, locationX, locationY);
	}
	
	// name of customer is 1 character long should work
	@Test
	public void nameOfCustomIs1char() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", "L", mobileNumber, locationX, locationY);
		assertEquals(custom.getName(), "L");
	}
	
	// name of Customer longer than 20 characters should throw an exception 
	@Test	(expected = CustomerException.class)
	public void nameLongerThan20Char() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", "RAINING ON YOUR BODY ~~ START RUBBING ON YO BODY ~~~", mobileNumber, locationX, locationY);
	}
	
	// DIstance more than 10 blocks east
	@Test (expected = CustomerException.class)
	public void moreThan10BlocksEast() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, mobileNumber, 11, -6);
	}
	
	// Distance more than 10 blocks west
	@Test (expected = CustomerException.class)
	public void moreThan10BlocksWest() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, mobileNumber, -11, 3);
	}
	
	// Distance more than 10 blocks north
	@Test (expected = CustomerException.class)
	public void moreThan10BlocksNorth() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, mobileNumber, 4, 12);
	}
	
	// Distance more than 10 blocks south
	@Test (expected = CustomerException.class)
	public void moreThan10BlocksSouth() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, mobileNumber, 4, -13);
	}
	
	// name can't be null
	@Test (expected = CustomerException.class)
	public void nameNotAllowedToBeNull() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", null, mobileNumber, locationX, locationY);
	}
	
	// mobile can't be null
	@Test (expected = CustomerException.class)
	public void mobileNotAllowedToBeNull() throws CustomerException {
		custom = CustomerFactory.getCustomer("PUC", name, null, locationX, locationY);
	}
	
}
