package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	private String type;
	protected double price;
	private double cost;
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		// TO DO	
		if (quantity < 1 || quantity > 10) {
			throw new PizzaException("Atleast ONE pizza must be ordered OR Can only order less than 10 pizzas");
		}
		int order = 0;		// used for checking orderTime
		int deliver = 0;	// used for checking deliveryTime
		
		for (int i = 0; i < orderTime.getHour(); i++) {
			order += 60;	// for example, if time is 06:20, order will be '360' ,   6 * 60 = 360
		}
		
		if (deliveryTime.getHour() == 0) {
			deliver = 1440;		// if deliveryTime is (00:00) which is 12am	make deliver = 1440 which is 24 * 60
		} else {
			for (int i = 0; i < deliveryTime.getHour(); i++) {
				deliver += 60;
			}
		}
		order += orderTime.getMinute();		// order of 360 + 20 = 380
		deliver += deliveryTime.getMinute();
		
		// 1140 = 19:00 (7pm) 	and 	1380 = 23:00 (11pm)
		// Checks whether the order is made between 7pm to 11pm
		if (order < 1140 || order > 1380) {
			throw new PizzaException("Order only from 7pm (19:00) to 11pm (23:00)");
		}
		
		int orderFinished = order + 10;	// + 10 because it takes 10 minutes to make the Pizza
		int orderDeliverDifference = deliver - order;	// for example, deliver = 90 and order = 60,   90 - 60 -> difference is 30
		
		if (orderFinished > deliver) {
			throw new PizzaException("Order takes 10 minutes to cook.Delivery time needs to be atleast 10 mins after Order Time");
		} else if (orderDeliverDifference > 60) {
			throw new PizzaException("Pizza thrown out. It took longer than 60 minutes to make and deliver.");
		}
		
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.type = type;
		this.price = price;
	}

	/**
	 * Calculates how much a pizza would cost to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		// TO DO
		if (price == 8) {	// Margherita
			cost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost();
		} else if (price == 10) { // Vegetarian
			cost = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost() + PizzaTopping.EGGPLANT.getCost() 
					+ PizzaTopping.MUSHROOM.getCost() + PizzaTopping.CAPSICUM.getCost();
		} else {	// Meat lovers
			cost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost() + PizzaTopping.BACON.getCost()
					+ PizzaTopping.SALAMI.getCost() + PizzaTopping.PEPPERONI.getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		// TO DO
		calculateCostPerPizza();
		return cost;	// returns 1.5, 5.5 or 5
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		// TO DO
		return price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		// TO DO
		calculateCostPerPizza();
		return cost * quantity;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		// TO DO
		calculateCostPerPizza();
		return price * quantity;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		// TO DO
		calculateCostPerPizza();
		return getOrderPrice() - getOrderCost();
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		// TO DO
		if ((price == 8) && (topping == PizzaTopping.TOMATO || topping == PizzaTopping.CHEESE)) {
			return true;
		} else if ((price == 10) && (topping == PizzaTopping.TOMATO || topping == PizzaTopping.CHEESE || 
				topping == PizzaTopping.EGGPLANT || topping == PizzaTopping.MUSHROOM || topping == PizzaTopping.CAPSICUM)) {
			return true;
		} else if ((price == 12) && (topping == PizzaTopping.TOMATO || topping == PizzaTopping.CHEESE || 
				topping == PizzaTopping.BACON || topping == PizzaTopping.PEPPERONI || topping == PizzaTopping.SALAMI)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		// TO DO
		return quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		// TO DO
		return type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}
