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
	private Pizza margh;		// margherita
	private Pizza vegeta;		// vegetarian
	private Pizza meatLover;	// meat lover
	
	private Pizza notGonnaWork;
	
	private LocalTime order = LocalTime.parse("20:30:00");
	private LocalTime deliver = LocalTime.parse("20:45:00");
	
	
	// Testing orderTime of 8:30pm and deliveryTime of 8:45pm which are both valid
	@Before
	public void createMargherita() throws PizzaException {
		margh = new MargheritaPizza(3, order, deliver);
		vegeta = new VegetarianPizza(6, order, deliver);
		meatLover = new MeatLoversPizza(6, order, deliver);
	}
	
	// Quantity '3' should be valid
	@Test
	public void quantityTestOne() throws PizzaException {
		assertEquals(margh.getQuantity(), 3);
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
	
	// Delivery time has to be at least 10 mins more than order time - test one
	@Test (expected = PizzaException.class)
	public void deliveryTimeAtleast10MinsTest() throws PizzaException {
		notGonnaWork = new MargheritaPizza(9, LocalTime.parse("19:45:00"), LocalTime.parse("19:50:00"));
	}
	
	// Delivery time has to be at least 10 mins more than order time - test two
	@Test (expected = PizzaException.class)
	public void deliveryTimeAtleast10MinsTestTwo() throws PizzaException {
		notGonnaWork = new MargheritaPizza(9, LocalTime.parse("21:21:00"), LocalTime.parse("21:30:00"));
	}
	//	///	///	///	///	///	//	//	//	///	////	/////	////	////	////	/////	/////	/////	////	/////	///

	// getCostPerPizza Test for MARGHERITA and at the same time indirectly testing calculateCostPerPizza
	@Test
	public void calcPizzaTestForMargherita() throws PizzaException {
		double expected = 1.5;
		margh.calculateCostPerPizza();
		assertEquals(margh.getCostPerPizza(), expected, 0.000001);
	}
	
	// getCostPerPizza Test for MEATLOVERS and at the same time indirectly testing calculateCostPerPizza
	@Test
	public void calcPizzaTestForMeatLovers() throws PizzaException {
		double expected = 5.0;	// Meat Lovers pizza toppings cost 5.0
		meatLover.calculateCostPerPizza();
		assertEquals(meatLover.getCostPerPizza(), expected, 0.000001);
	}
	
	// getCostPerPizza Test for VEGETARIAN and at the same time indirectly testing calculateCostPerPizza
	@Test
	public void calcPizzaTestForVegetarian() throws PizzaException {
		double expected = 5.5;	// Vegetarian pizza toppings cost 5.5
		vegeta.calculateCostPerPizza();
		assertEquals(vegeta.getCostPerPizza(), expected, 0.000001);
	}
	
	// getPricePerPizza Test for MARGHERITA
	@Test
	public void getPricePerPizzaMargheritaTest() throws PizzaException {
		assertEquals(margh.getPricePerPizza(), 8, 0.000001);
	}
	
	// getPricePerPizza Test for MEATLOVERS
	@Test
	public void getPricePerPizzaMeatLoversTest() throws PizzaException {
		assertEquals(meatLover.getPricePerPizza(), 12, 0.000001);
	}
	
	// getPricePerPizza Test for MEATLOVERS
	@Test
	public void getPricePerPizzaVegetarianTest() throws PizzaException {
		assertEquals(vegeta.getPricePerPizza(), 10, 0.000001);
	}
	
	// getOrderCost Test for Margherita - Test One
	@Test
	public void getOrderCostMargheritaTestOne() throws PizzaException {
		double expected = 6;
		Pizza marga = new MargheritaPizza(4, order, deliver);	// Cost to make * quantity = 1.5 * 4 = 6
		marga.calculateCostPerPizza();
		assertEquals(marga.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Margherita - Test Two
	@Test
	public void getOrderCostMargheritaTestTwo() throws PizzaException {
		double expected = 12;
		Pizza marga = new MargheritaPizza(8, order, deliver);	// Cost to make * quantity = 1.5 * 8 = 12
		marga.calculateCostPerPizza();
		assertEquals(marga.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Margherita - Test Three
	@Test
	public void getOrderCostMargheritaTestThree() throws PizzaException {
		double expected = 15;
		Pizza marga = new MargheritaPizza(10, order, deliver);	// Cost to make * quantity = 1.5 * 10 = 15
		marga.calculateCostPerPizza();
		assertEquals(marga.getOrderCost(), expected, 0.000001);
	}
	
	// getOrderCost Test for Meat Lover - Test One
	@Test
	public void getOrderCostMeatLoverTestOne() throws PizzaException {
		double expected = 20;
		Pizza ml = new MeatLoversPizza(4, order, deliver);	// Cost to make * quantity = 5 * 4 = 20
		ml.calculateCostPerPizza();
		assertEquals(ml.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for MeatLover - Test Two
	@Test
	public void getOrderCostMeatLoverTestTwo() throws PizzaException {
		double expected = 40;
		Pizza ml = new MeatLoversPizza(8, order, deliver);	// Cost to make * quantity = 5 * 8 = 40
		ml.calculateCostPerPizza();
		assertEquals(ml.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for MeatLover - Test Three
	@Test
	public void getOrderCostMeatLoverTestThree() throws PizzaException {
		double expected = 50;
		Pizza ml = new MeatLoversPizza(10, order, deliver);	// Cost to make * quantity = 5 * 10 = 50
		ml.calculateCostPerPizza();
		assertEquals(ml.getOrderCost(), expected, 0.000001);
	}
	
	// getOrderCost Test for Vegetarian - Test One
	@Test
	public void getOrderCostVegetarianTestOne() throws PizzaException {
		double expected = 22;
		Pizza vg = new VegetarianPizza(4, order, deliver);	// Cost to make * quantity = 5.5 * 4 = 22
		vg.calculateCostPerPizza();
		assertEquals(vg.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Vegetarian - Test Two
	@Test
	public void getOrderCostVegetarianTestTwo() throws PizzaException {
		double expected = 44;
		Pizza vg = new VegetarianPizza(8, order, deliver);	// Cost to make * quantity = 5.5 * 8 = 44
		vg.calculateCostPerPizza();
		assertEquals(vg.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Vegetarian - Test Three
	@Test
	public void getOrderCostVegetarianTestThree() throws PizzaException {
		double expected = 55;
		Pizza vg = new VegetarianPizza(10, order, deliver);	// Cost to make * quantity = 5.5 * 10 = 55
		vg.calculateCostPerPizza();
		assertEquals(vg.getOrderCost(), expected, 0.000001);
	}
	
	// getOrderPrice Test for Margherita - Test One
	@Test
	public void getOrderPriceMargheritaTestOne() throws PizzaException {
		double expected = 32;		// 8 * 4 = 32
		Pizza mg = new MargheritaPizza(4, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
	
	// getOrderPrice Test for Margherita - Test Two
	@Test
	public void getOrderPriceMargheritaTestTwo() throws PizzaException {
		double expected = 64;		// 8 * 8 = 64
		Pizza mg = new MargheritaPizza(8, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
		
	// getOrderPrice Test for Margherita - Test Three
	@Test
	public void getOrderPriceMargheritaTestThree() throws PizzaException {
		double expected = 80;		// 8 * 10 = 80
		Pizza mg = new MargheritaPizza(10, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
	
	// getOrderPrice Test for MeatLover - Test One
	@Test
	public void getOrderPriceMeatLoverTestOne() throws PizzaException {
		double expected = 48;		// 12 * 4 = 48
		Pizza mg = new MeatLoversPizza(4, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
		
	// getOrderPrice Test for MeatLover - Test Two
	@Test
	public void getOrderPriceMeatLoverTestTwo() throws PizzaException {
		double expected = 96;		// 12 * 8 = 96
		Pizza mg = new MeatLoversPizza(8, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
			
	// getOrderPrice Test for MeatLover - Test Three
	@Test
	public void getOrderPriceMeatLoverTestThree() throws PizzaException {
		double expected = 120;		// 12 * 10 = 120
		Pizza mg = new MeatLoversPizza(10, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}

	// getOrderPrice Test for MeatLover - Test One
	@Test
	public void getOrderPriceVegetarianTestOne() throws PizzaException {
		double expected = 40;		// 10 * 4 = 40
		Pizza mg = new VegetarianPizza(4, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
		
	// getOrderPrice Test for MeatLover - Test Two
	@Test
	public void getOrderPriceVegetarianTestTwo() throws PizzaException {
		double expected = 80;		// 10 * 8 = 80
		Pizza mg = new VegetarianPizza(8, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
			
	// getOrderPrice Test for MeatLover - Test Three
	@Test
	public void getOrderPriceVegetarianTestThree() throws PizzaException {
		double expected = 100;		// 10 * 10 = 100
		Pizza mg = new VegetarianPizza(10, order, deliver);
		mg.calculateCostPerPizza();
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}	
	
}
