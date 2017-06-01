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
 * @author Levinard Hugo (Person A)
 * 
 *
 */
public class CustomerTests {
	
	private Customer dl;
	private Customer errorCustomer;		// used for exception tests
	
	private String name = "Arthas Menethil";
	private String mobileNumber = "0902007865";
	private int locationX = 2;
	private int locationY = 3;
	
	// Indirectly testing all parameters
	@Before		
	public void createCustomer() throws CustomerException {
		dl = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	// Null name 
	@Test (expected = CustomerException.class)
	public void NameNullExceptionTest() throws CustomerException{
		errorCustomer = new DriverDeliveryCustomer(null,"1234",0,0);
	}
	
	// Null mobile
	@Test (expected = CustomerException.class)
	public void MobileNumberNullExceptionTest() throws CustomerException{
		errorCustomer = new DriverDeliveryCustomer("official",null,0,0);
	}
	
	// test getName()
	@Test
	public void returnName() throws CustomerException {
		assertEquals(dl.getName(), "Arthas Menethil");
	}
	
	// test getName() between 1 - 20 characters, in this case just 1 character should be VALID
	@Test
	public void nameOneCharacter() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer("L", mobileNumber, locationX, locationY);
		assertEquals(driver.getName(), "L");
	}

	// test getName() between 1 - 20 characters, in this case just 20 character should be VALID
	@Test
	public void nameTwentyCharacters() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer("Levinard Christopher", mobileNumber, locationX, locationY);
		assertEquals(driver.getName().length(), 20);
	}
		
	// test returnMobileNumber()
	@Test
	public void returnMobileNumber() throws CustomerException {
		assertEquals(dl.getMobileNumber(), "0902007865");
	}
	
	// test getCustomerType()
	@Test
	public void getCustomerTypeTest() throws CustomerException {
		assertEquals(dl.getCustomerType(), "Driver Delivery");
	}
	
	// test getCustomerType() PickUp
	@Test
	public void getCustomerTypeForPU() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals(pu.getCustomerType(), "Pick Up");
	}

	// test getCustomerType() PickUp
	@Test
	public void getCustomerTypeForDrone() throws CustomerException {
		Customer dnc = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals(dnc.getCustomerType(), "Drone Delivery");
	}
		
	// Test getLocationX()
	@Test
	public void getLocationXTest() throws CustomerException {
		assertEquals(dl.getLocationX(), 2);
	}
	
	// Test getLocationY
	@Test
	public void getLocationYTest() throws CustomerException {
		assertEquals(dl.getLocationY(), 3);
	}
	
	// Test getDeliveryDistance() should be a Manhattan Distance |0 - 2| + |0 - 3| = 5
	@Test
	public void getDeliveryDistanceTestForDriverCustomer() throws CustomerException {
		double expected = 5;
		assertEquals(dl.getDeliveryDistance(), expected, 0.000001);
	}
	
	// Test getDeliveryDistance() Test 2 Driver Customer
	@Test
	public void getDeliveryDistanceTest2ForDriverCustomer() throws CustomerException {
		double expected;
		Customer cus = new DriverDeliveryCustomer(name, mobileNumber, -3, 1);	// Manhattan Distance should be 4
		assertEquals(cus.getDeliveryDistance(), (expected = 4), 0.0001);
	}
	
	// Test getDeliveryDistance() Test 3 for Driver Customer
	@Test
	public void getDeliveryDistanceTest3ForDriverCustomer() throws CustomerException {
		double expected = 15;
		Customer cus = new DriverDeliveryCustomer(name, mobileNumber, -8, 7);	// Manhattan Distance should be 15
		assertEquals(cus.getDeliveryDistance(), 15, 0.001);
	}
	
	// Test getDeliveryDistance() Euclidean 
	@Test
	public void getDeliveryDistanceTestForDrone() throws CustomerException {
		Customer ddc = new DroneDeliveryCustomer(name, mobileNumber, 3, -5);	// Euclidean should be around 5.83
		assertEquals(ddc.getDeliveryDistance(), 5.83, 0.001);
	}
	
	// Test getDeliveryDistance() test 2 Eulidean
	@Test
	public void getDeliveryDistanceTest2ForDrone() throws CustomerException {
		double xpected;
		Customer ddc = new DroneDeliveryCustomer(name, mobileNumber, 8, -6);	// Euclidean is 10
		assertEquals(ddc.getDeliveryDistance(), (xpected = 10), 0.01);
	}
	
	// Test getDeliveryDistance() test 3 Eulidean
	@Test
	public void getDeliveryDistanceTest3ForDrone() throws CustomerException {
		double xpected = 5.385;
		Customer ddc = new DroneDeliveryCustomer(name, mobileNumber, -5, 2);	// Euclidean result should be around 5.385
		assertEquals(ddc.getDeliveryDistance(), xpected, 0.001);
		
	}
	
	// Test getDeliveryDistance() test EXACT value
	@Test
	public void getDeliveryDistanceTestForDroneExact() throws CustomerException {
		double expected = 7.21110255093;		// not exactly the exact value, but this is exact enough #exactception
		Customer ddc = new DroneDeliveryCustomer(name, mobileNumber, -6, 4);	
		assertEquals(ddc.getDeliveryDistance(), expected, 0.00000000001);
	}
	
	// Test getDeliveryDistance for Pick up should always be zero
	@Test
	public void getDeliveryDistancePickUp() throws CustomerException {
		double expected = 0.0;
		Customer pu = new PickUpCustomer(name, mobileNumber, 0,0);
		assertEquals(pu.getDeliveryDistance(), expected, 0.0001);
	}
	
	//	///	///	//	///	//	///	///	//	//	//	//	//	//	//	/	/	/	/	//	/	///	/	////	/	///	/	///	/	//
	
	// If it's a PickUpCustomer class, location x and y can only be 0, in this test locationX is a positive number
	@Test (expected = CustomerException.class)
	public void locXforPUPositive() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, 1, 0);
	}
	
	// in this test locationY is a positive number
	@Test (expected = CustomerException.class)
	public void locYforPUPositive() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, 0, 3);
	}
	
	// in this test locationX is a negative number
	@Test (expected = CustomerException.class)
	public void locXforPUNegative() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, -2, 0);
	}
	
	// in this test locationY is a negative number
	@Test (expected = CustomerException.class)
	public void locYforPUNegative() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, 0, -1);
	}
	
	// in this test both location x and y are positive
	@Test (expected = CustomerException.class)
	public void XandYPositive() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, 1, 3);
	}

	// in this test both location x and y are negative
	@Test (expected = CustomerException.class)
	public void XandYNegative() throws CustomerException {
		Customer pu = new PickUpCustomer(name, mobileNumber, -1, -3);
	}	
	
	// Whitespace name
	@Test (expected = CustomerException.class)
	public void whiteSpaceName() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer("", mobileNumber, locationX, locationY);
	}
	
	// name longer than 20 characters
	@Test (expected = CustomerException.class)
	public void longerThan20CharactersName() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer("I enjoyed this assignment very much", mobileNumber, locationX, locationY);
	}
	
	// Mobile num longer than 10 digit
	@Test (expected = CustomerException.class)
	public void mobileNumLongerThan10digit() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, "012345678931", locationX, locationY);
	}	
	
	// Mobile num less than 10 digit
	@Test (expected = CustomerException.class)
	public void mobileNumLessThan10() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, "09418", locationX, locationY);
	}
	
	// Mobile num doesn't start at 0
	@Test (expected = CustomerException.class)
	public void mobileNumLongerNotStartAtZero() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, "9878789675", locationX, locationY);
	}	
		
	// mobile with a letter 
	@Test (expected = CustomerException.class)
	public void mobileWithaLetter() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, "0123456r78", locationX, locationY);
	}
	
	// mobile with a word
	@Test (expected = CustomerException.class)
	public void mobileWithAWord() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, "012LOL3452", locationX, locationY);
	}	
	
	// location X longer than 10 blocks positive
	@Test (expected = CustomerException.class)
	public void locationXmorethan10() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, mobileNumber, 11, locationY);
	}
	
	// location Y longer than 10 blocks positive
	@Test (expected = CustomerException.class)
	public void locationYmorethan10() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, mobileNumber, locationX, 12);
	}
	
	// location X longer than 10 blocks negative
	@Test (expected = CustomerException.class)
	public void locationXmorethan10Negative() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, mobileNumber, -11, locationY);
	}
	
	// location Y longer than 10 blocks negative
	@Test (expected = CustomerException.class)
	public void locationYmorethan10Negative() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, mobileNumber, locationX, -12);
	}
	
	
	// both location longer than 10 positive
	@Test (expected = CustomerException.class)
	public void bothLocationPositiveAndOver10() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, mobileNumber, 12, 13);
	}
	
	// Both location longer than 10 negative
	@Test (expected = CustomerException.class)
	public void bothLocationNegativeAndOver10() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer(name, mobileNumber, -14, -12);
	}
		
	// every single parameter incorrect
	@Test (expected = CustomerException.class)
	public void allParamWrong() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer("", "5612231398", -199, 18);
	}
	
	// every single parameter incorrect test2
	@Test (expected = CustomerException.class)
	public void allParamWrongT2() throws CustomerException {
		Customer driver = new DriverDeliveryCustomer("I LOVE WRITING UNIT TEST WOOOOO~~~~", "05611398", 69, -69);
	}
	
}
