package asgn2Tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import asgn2Customers.*;
import asgn2Exceptions.CustomerException;


/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	// TO DO
	private Customer pickup;
	private Customer delivery;
	private Customer drone;
	
	private Customer errorCustomer;
	
	@Before
	public void TestConstructor() throws CustomerException{
		pickup = new PickUpCustomer("official","1234", 0,0 );
		delivery = new DriverDeliveryCustomer("official2", "2345", 1, 1);
		drone = new DroneDeliveryCustomer("official3", "3456", 1, 1);
	}
	
	@Test (expected = CustomerException.class)
	public void NameNullExceptionTest() throws CustomerException{
		errorCustomer = new DriverDeliveryCustomer(null,"1234",0,0);
	}
	
	@Test (expected = CustomerException.class)
	public void MobileNumberNullExceptionTest() throws CustomerException{
		errorCustomer = new DriverDeliveryCustomer("official",null,0,0);
	}
	
	
	@Test
	public void CustomergetNameTest() {
		String expected = "official2";
		assertEquals(delivery.getName(),expected );
	}
	
	@Test
	public void CustomergetMobileNumberTest(){
		String expected = "2345";
		assertEquals(delivery.getMobileNumber(), expected);
	}
	
	@Test
	public void CustomergetCustomerTypeTest(){
		// to do
	}
	
	
	
	
	
	
	
	
}
