package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * 
 *  A class that represents a vegetarian pizza made at the Pizza Palace restaurant. 
 *  The vegetarian pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Levinard Hugo
 *
 */
public class VegetarianPizza extends Pizza {

	/**
	 * 
	 *  This class represents a vegetarian pizza made at the  Pizza Palace restaurant. The vegetarian pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
     * <P> PRE: TRUE
	 * <P> POST: All field values including the cost per pizza are set
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid, and if quantity is less than 1 or more than 10,  if orderTime and deliverTime are not made between 7pm (19:00) and 11pm (23:00), throw exception if deliveryTime is not at least 10 minutes longer than orderTime, lastly if deliveryTime is more than 1 hour longer than orderTime then throw exception 
	 *
	 */
	public VegetarianPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		super(quantity, orderTime, deliveryTime, "Vegetarian", 10);
	}

}
