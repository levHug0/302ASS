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
 * @author Raj Rosello
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
	public void quantityTestOne(){
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
	
	// Indirectly testing order at 11pm (which is the last time anyone is allowed to order)
	@Test
	public void OrderingAt11pm() throws PizzaException {
		LocalTime ord = LocalTime.parse("23:00");
		LocalTime del = LocalTime.parse("23:59");
		Pizza test = new MargheritaPizza(9, ord, del);
		assertEquals(test.getQuantity(), 9);
	}
	
	// Indirectly testing order at 11pm (which is the last time anyone is allowed to order)
	@Test
	public void OrderingAt11pmAndDeliverAt12am() throws PizzaException {
		LocalTime ord = LocalTime.parse("23:00");
		LocalTime del = LocalTime.parse("00:00");	// 12am 
		Pizza test = new MargheritaPizza(9, ord, del);
		assertEquals(test.getQuantity(), 9);
	}	
	
	// Testing order at 11pm, but delivery time took longer than 1 hour
	@Test (expected = PizzaException.class)
	public void OrderAt11pmButDeliveryLongerThan1hr() throws PizzaException {
		LocalTime ord = LocalTime.parse("23:00");
		LocalTime del = LocalTime.parse("00:01");	// this looks like 1501 which is longer than 60 mins
		Pizza test = new MargheritaPizza(8, ord, del);
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
	public void calcPizzaTestForMargherita(){
		double expected = 1.5;
		assertEquals(margh.getCostPerPizza(), expected, 0.000001);
	}
	
	// getCostPerPizza Test for MEATLOVERS and at the same time indirectly testing calculateCostPerPizza
	@Test
	public void calcPizzaTestForMeatLovers()  {
		double expected = 5.0;	// Meat Lovers pizza toppings cost 5.0

		assertEquals(meatLover.getCostPerPizza(), expected, 0.000001);
	}
	
	// getCostPerPizza Test for VEGETARIAN and at the same time indirectly testing calculateCostPerPizza
	@Test
	public void calcPizzaTestForVegetarian(){
		double expected = 5.5;	// Vegetarian pizza toppings cost 5.5
	
		assertEquals(vegeta.getCostPerPizza(), expected, 0.000001);
	}
	
	// getPricePerPizza Test for MARGHERITA
	@Test
	public void getPricePerPizzaMargheritaTest()  {
		assertEquals(margh.getPricePerPizza(), 8, 0.000001);
	}
	
	// getPricePerPizza Test for MEATLOVERS
	@Test
	public void getPricePerPizzaMeatLoversTest()  {
		assertEquals(meatLover.getPricePerPizza(), 12, 0.000001);
	}
	
	// getPricePerPizza Test for Vegetarian
	@Test
	public void getPricePerPizzaVegetarianTest() {
		assertEquals(vegeta.getPricePerPizza(), 10, 0.000001);
	}
	
	// getOrderCost Test for Margherita - Test One
	@Test
	public void getOrderCostMargheritaTestOne() throws PizzaException {
		double expected = 6;
		Pizza marga = new MargheritaPizza(4, order, deliver);	// Cost to make * quantity = 1.5 * 4 = 6
		assertEquals(marga.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Margherita - Test Two
	@Test
	public void getOrderCostMargheritaTestTwo() throws PizzaException {
		double expected = 12;
		Pizza marga = new MargheritaPizza(8, order, deliver);	// Cost to make * quantity = 1.5 * 8 = 12
		assertEquals(marga.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Margherita - Test Three
	@Test
	public void getOrderCostMargheritaTestThree() throws PizzaException {
		double expected = 15;
		Pizza marga = new MargheritaPizza(10, order, deliver);	// Cost to make * quantity = 1.5 * 10 = 15
		assertEquals(marga.getOrderCost(), expected, 0.000001);
	}
	
	// getOrderCost Test for Meat Lover - Test One
	@Test
	public void getOrderCostMeatLoverTestOne() throws PizzaException {
		double expected = 20;
		Pizza ml = new MeatLoversPizza(4, order, deliver);	// Cost to make * quantity = 5 * 4 = 20
		assertEquals(ml.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for MeatLover - Test Two
	@Test
	public void getOrderCostMeatLoverTestTwo() throws PizzaException {
		double expected = 40;
		Pizza ml = new MeatLoversPizza(8, order, deliver);	// Cost to make * quantity = 5 * 8 = 40
		assertEquals(ml.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for MeatLover - Test Three
	@Test
	public void getOrderCostMeatLoverTestThree() throws PizzaException {
		double expected = 50;
		Pizza ml = new MeatLoversPizza(10, order, deliver);	// Cost to make * quantity = 5 * 10 = 50
		assertEquals(ml.getOrderCost(), expected, 0.000001);
	}
	
	// getOrderCost Test for Vegetarian - Test One
	@Test
	public void getOrderCostVegetarianTestOne() throws PizzaException {
		double expected = 22;
		Pizza vg = new VegetarianPizza(4, order, deliver);	// Cost to make * quantity = 5.5 * 4 = 22
		assertEquals(vg.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Vegetarian - Test Two
	@Test
	public void getOrderCostVegetarianTestTwo() throws PizzaException {
		double expected = 44;
		Pizza vg = new VegetarianPizza(8, order, deliver);	// Cost to make * quantity = 5.5 * 8 = 44
		assertEquals(vg.getOrderCost(), expected, 0.000001);
	}
	// getOrderCost Test for Vegetarian - Test Three
	@Test
	public void getOrderCostVegetarianTestThree() throws PizzaException {
		double expected = 55;
		Pizza vg = new VegetarianPizza(10, order, deliver);	// Cost to make * quantity = 5.5 * 10 = 55
		assertEquals(vg.getOrderCost(), expected, 0.000001);
	}
	
	// getOrderPrice Test for Margherita - Test One
	@Test
	public void getOrderPriceMargheritaTestOne() throws PizzaException {
		double expected = 32;		// 8 * 4 = 32
		Pizza mg = new MargheritaPizza(4, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
	
	// getOrderPrice Test for Margherita - Test Two
	@Test
	public void getOrderPriceMargheritaTestTwo() throws PizzaException {
		double expected = 64;		// 8 * 8 = 64
		Pizza mg = new MargheritaPizza(8, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
		
	// getOrderPrice Test for Margherita - Test Three
	@Test
	public void getOrderPriceMargheritaTestThree() throws PizzaException {
		double expected = 80;		// 8 * 10 = 80
		Pizza mg = new MargheritaPizza(10, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
	
	// getOrderPrice Test for MeatLover - Test One
	@Test
	public void getOrderPriceMeatLoverTestOne() throws PizzaException {
		double expected = 48;		// 12 * 4 = 48
		Pizza mg = new MeatLoversPizza(4, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
		
	// getOrderPrice Test for MeatLover - Test Two
	@Test
	public void getOrderPriceMeatLoverTestTwo() throws PizzaException {
		double expected = 96;		// 12 * 8 = 96
		Pizza mg = new MeatLoversPizza(8, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
			
	// getOrderPrice Test for MeatLover - Test Three
	@Test
	public void getOrderPriceMeatLoverTestThree() throws PizzaException {
		double expected = 120;		// 12 * 10 = 120
		Pizza mg = new MeatLoversPizza(10, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}

	// getOrderPrice Test for MeatLover - Test One
	@Test
	public void getOrderPriceVegetarianTestOne() throws PizzaException {
		double expected = 40;		// 10 * 4 = 40
		Pizza mg = new VegetarianPizza(4, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
		
	// getOrderPrice Test for MeatLover - Test Two
	@Test
	public void getOrderPriceVegetarianTestTwo() throws PizzaException {
		double expected = 80;		// 10 * 8 = 80
		Pizza mg = new VegetarianPizza(8, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}
			
	// getOrderPrice Test for MeatLover - Test Three
	@Test
	public void getOrderPriceVegetarianTestThree() throws PizzaException {
		double expected = 100;		// 10 * 10 = 100
		Pizza mg = new VegetarianPizza(10, order, deliver);
		assertEquals(mg.getOrderPrice(),expected,0.000001);
	}	
	
	// getOrderProfit Test for Margherita 
	@Test
	public void getOrderProfitMargheritaT1() throws PizzaException {
		double expected = 19.5;		// Price 8, cost to make 1.5, profit = 8 - 1.5 = 6.5 * 3 (quantity) = 19.5
		assertEquals(margh.getOrderProfit(), expected ,0.00001);
	}
	
	// getOrderProfit Test for Margherita
	@Test
	public void getOrderProfitMargheritaT2() throws PizzaException {
		double expected = 6.5;		// Price 8, cost to make 1.5, profit = 8 - 1.5 = 6.5
		Pizza mg = new MargheritaPizza(1, order, deliver);	
		assertEquals(mg.getOrderProfit(), expected ,0.00001);
	}	

	// getOrderProfit Test for Margherita
	@Test
	public void getOrderProfitMargheritaT3() throws PizzaException {
		double expected = 65;		// Price 8, cost to make 1.5, profit = 8 - 1.5 = 6.5 * 10 = 65
		Pizza mg = new MargheritaPizza(10, order, deliver);	
		assertEquals(mg.getOrderProfit(), expected ,0.00001);
	}	
	

	// getOrderProfit Test for MeatLovers
	@Test
	public void getOrderProfitMeatLoverT1() throws PizzaException {
		double expected = 70;		// Price 12, cost to make 5, profit = 12 - 5 = 7 * 10 = 70
		Pizza mg = new MeatLoversPizza(10, order, deliver);	
		assertEquals(mg.getOrderProfit(), expected ,0.00001);
	}	
	// getOrderProfit Test for MeatLovers
	@Test
	public void getOrderProfitMeatLoverT2() throws PizzaException {
		double expected = 7;		// Price 12, cost to make 5, profit = 12 - 5 = 7 
		Pizza mg = new MeatLoversPizza(1, order, deliver);	
		assertEquals(mg.getOrderProfit(), expected ,0.00001);
	}	
	// getOrderProfit Test for MeatLovers
	@Test
	public void getOrderProfitMeatLoverT3() throws PizzaException {
		double expected = 21;		// Price 12, cost to make 5, profit = 12 - 5 = 7 * 3 = 21
		Pizza mg = new MeatLoversPizza(3, order, deliver);	
		assertEquals(mg.getOrderProfit(), expected ,0.00001);
	}	
	
	// getOrderProfit Test for Vegetarian
	@Test
	public void getOrderProfitVegetarianT1() throws PizzaException {
		double expected = 4.5;		// Price 10, cost to make 5.5, profit = 10 - 5.5 = 4.5 
		Pizza mg = new VegetarianPizza(1, order, deliver);	
		assertEquals(mg.getOrderProfit(), expected ,0.00001);
	}	
	
	// getOrderProfit Test for Vegetarian
	@Test
	public void getOrderProfitVegetarianT2() throws PizzaException {
		double expected = 22.5;		// 4.5 * 5 = 22.5
		Pizza mg = new VegetarianPizza(5, order, deliver);
		assertEquals(mg.getOrderProfit(), expected, 0.000001);
	}
	
	// getOrderProfit Test for Vegetarian
	@Test
	public void getOrderProfitVegetarianT3() throws PizzaException {
		double expected = 45;		// 4.5 * 10 = 45
		Pizza mg = new VegetarianPizza(10, order, deliver);
		assertEquals(mg.getOrderProfit(), expected, 0.000001);
	}	
	
	// containsTopping Test for Margherita - True
	@Test
	public void containsToppingMargheritaT1() {
		assertTrue(margh.containsTopping(PizzaTopping.TOMATO));
	}
	// containsTopping Test for Margherita - True
	@Test
	public void containsToppingMargheritaT2() {
		assertTrue(margh.containsTopping(PizzaTopping.CHEESE));
	}
	// containsTopping Test for Margherita - False
	@Test
	public void containsToppingMargheritaT3()  {
		assertFalse(margh.containsTopping(PizzaTopping.EGGPLANT));
	}
	// containsTopping Test for Margherita - False
	@Test
	public void containsToppingMargheritaT4()  {
		assertFalse(margh.containsTopping(PizzaTopping.MUSHROOM));
	}
	// containsTopping Test for Margherita - False
	@Test
	public void containsToppingMargheritaT5() {
		assertFalse(margh.containsTopping(PizzaTopping.CAPSICUM));
	}
	
	// containsTopping Test for Margherita - False
	@Test
	public void containsToppingMargheritaT6()  {
		assertFalse(margh.containsTopping(PizzaTopping.BACON));
	}	
	// containsTopping Test for Margherita - False
	@Test
	public void containsToppingMargheritaT7()  {
		assertFalse(margh.containsTopping(PizzaTopping.PEPPERONI));
	}	
	// containsTopping Test for Margherita - False
	@Test
	public void containsToppingMargheritaT8()  {
		assertFalse(margh.containsTopping(PizzaTopping.SALAMI));
	}	
	
	// containsTopping Test for MeatLovers - true
	@Test
	public void containsToppingMeatLovers()  {
		assertTrue(meatLover.containsTopping(PizzaTopping.BACON));
	}	
	// containsTopping Test for MeatLovers - true
	@Test
	public void containsToppingMeatLoversT2()  {
		assertTrue(meatLover.containsTopping(PizzaTopping.PEPPERONI));
	}	
	// containsTopping Test for MeatLovers - true
	@Test
	public void containsToppingMeatLoversT3()  {
		assertTrue(meatLover.containsTopping(PizzaTopping.SALAMI));
	}	
	// containsTopping Test for MeatLovers - true
	@Test
	public void containsToppingMeatLoversT4()  {
		assertTrue(meatLover.containsTopping(PizzaTopping.TOMATO));
	}	
	// containsTopping Test for MeatLovers - true
	@Test
	public void containsToppingMeatLoversT5()  {
		assertTrue(meatLover.containsTopping(PizzaTopping.CHEESE));
	}	
	
	// containsTopping Test for MeatLovers - False
	@Test
	public void containsToppingMeatLoversT6()  {
		assertFalse(meatLover.containsTopping(PizzaTopping.CAPSICUM));
	}	
	// containsTopping Test for MeatLovers - False
	@Test
	public void containsToppingMeatLoversT7()  {
		assertFalse(meatLover.containsTopping(PizzaTopping.MUSHROOM));
	}	
	// containsTopping Test for MeatLovers - False
	@Test
	public void containsToppingMeatLoversT8()  {
		assertFalse(meatLover.containsTopping(PizzaTopping.EGGPLANT));
	}	
	
	// containsTopping Test for Vegetarian - true
	@Test
	public void containsToppingVegetaT1()  {
		assertTrue(vegeta.containsTopping(PizzaTopping.CAPSICUM));
	}	
	// containsTopping Test for Vegetarian - true
	@Test
	public void containsToppingVegetaT2()  {
		assertTrue(vegeta.containsTopping(PizzaTopping.MUSHROOM));
	}	
	// containsTopping Test for Vegetarian - true
	@Test
	public void containsToppingVegetaT3()  {
		assertTrue(vegeta.containsTopping(PizzaTopping.EGGPLANT));
	}
	// containsTopping Test for Vegetarian - true
	@Test
	public void containsToppingVegetaT4() {
		assertTrue(vegeta.containsTopping(PizzaTopping.CHEESE));
	}	
	// containsTopping Test for Vegetarian - true
	@Test
	public void containsToppingVegetaT5()  {
		assertTrue(vegeta.containsTopping(PizzaTopping.TOMATO));
	}
	// containsTopping Test for Vegetarian - false
	@Test
	public void containsToppingVegetaT6(){
		assertFalse(vegeta.containsTopping(PizzaTopping.SALAMI));
	}
	// containsTopping Test for Vegetarian - false
	@Test
	public void containsToppingVegetaT7(){
		assertFalse(vegeta.containsTopping(PizzaTopping.PEPPERONI));
	}
	// containsTopping Test for Vegetarian - false
	@Test
	public void containsToppingVegetaT8(){
		assertFalse(vegeta.containsTopping(PizzaTopping.BACON));
	}
	
	// Below are for testing 3 pizza types
	@Test
	public void pizzaTypeTestMargherita() {
		String expected = "Margherita";
		assertEquals(margh.getPizzaType(), expected);
	}
	@Test
	public void pizzaTypeTestVegetarian() {
		String expected = "Vegetarian";
		assertEquals(vegeta.getPizzaType(), expected);
	}
	@Test
	public void pizzaTypeTestMeatLovers() {
		String expected = "Meat Lovers";
		assertEquals(meatLover.getPizzaType(), expected);
	}
}
