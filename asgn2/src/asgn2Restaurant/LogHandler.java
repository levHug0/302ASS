package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.time.LocalTime;
import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Person A and Person B
 *
 */
public class LogHandler {

	static FileReader fl;
	static BufferedReader br;
	static FileReader countingLines;		//		THESE TWO	ARE USED FOR COUNTING THE LINES
	static LineNumberReader lnr;			//		THESE TWO	ARE USED FOR COUNTING THE LINES
	


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		// TO DO
		
		
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		// TO DO
		ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
		
		try {
			fl = new FileReader(filename);				// This is the main one
			countingLines = new FileReader(filename);	// this is used to count how many lines
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		lnr = new LineNumberReader(countingLines);
		br = new BufferedReader(fl);
		
		try {
			lnr.skip(Long.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int lineCount = lnr.getLineNumber() + 1; 		// + 1 because line number counts from 0
		
		for (int i = 1; i <= lineCount; i++) {
			String intToString = Integer.toString(i);		// converts int 'i' into a string
			Pizza createdPizza = createPizza(intToString);	// use the converted 'i' to be used as a string for the createPizza parameter
			pizzaList.add(createdPizza);
		}
		
		return pizzaList;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		// TO DO
		String intRegex = "[0-9]+";
		String nameRegex = "[A-Z][a-zA-Z ]{1,19}";
		String mobileNumberRegex = "0[0-9]{9}";
		String codeRegex1 = "PUC";
		String codeRegex2 = "DNC";
		String codeRegex3 = "DVC";
		
		if (line.matches(intRegex) == false) {
			throw new LogHandlerException("Parameter needs to be a numeric string");
		}
		
		int lineToInt = Integer.parseInt(line);
		String thisLine = null;
		
		for (int i = 0; i < lineToInt; i++) {
			try {
				thisLine = br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		String[] compareArray = line.split(",");
		String name = compareArray[2];
		String mobileNumber = compareArray[3];
		String customercode = compareArray[4];
		int locationX = Integer.parseInt(compareArray[5]);
		int locationY = Integer.parseInt(compareArray[6]);
		
		if(name.matches(nameRegex) == false){
			throw new LogHandlerException("Name Format should be maximum 20 characters long minimum of 1 letter and should not have only whitespace");
		} else if (mobileNumber.matches(mobileNumberRegex) == false){
			throw new LogHandlerException("Mobile number should start with a 0 and have 10 numbers");
		} else if ((customercode.matches(codeRegex1) || customercode.matches(codeRegex2) || customercode.matches((codeRegex3)) == false){
			throw new LogHandlerException("Customer code should be PUC, DNC or DVC");
		}
		
        Customer holder = CustomerFactory.getCustomer(customercode, name, mobileNumber, locationX, locationY);
		
        return holder;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		// TO DO

		String intRegex = "[0-9]+";
		String timeRegex = "(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)";
		String codeRegex = "PZ[VML]";
		
		if (line.matches(intRegex) == false) {
			throw new LogHandlerException("Parameter needs to be a numeric string");
		}
		int lineToInt = Integer.parseInt(line);
		
		String thisLine = null;
		
		for (int i = 0; i < lineToInt; i++) {
			try {
				thisLine = br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		String[] pizzaArr = thisLine.split(",");
		
		String orderTime = pizzaArr[0];
		String deliveryTime = pizzaArr[1];
		String pitsaCode = pizzaArr[7];		// doesn't need to be changed
		String pizzaQuantity = pizzaArr[8];
		
		if (orderTime.matches(timeRegex) == false) {
			throw new LogHandlerException("Order Time format incorrect. Correct format is HH:MM:SS");	
		} else if (deliveryTime.matches(timeRegex) == false) {
			throw new LogHandlerException("Delivery Time format incorrect. Correct format is HH:MM:SS");	
		} else if (pitsaCode.matches(codeRegex) == false) {
			throw new LogHandlerException("Incorrect pizzaCode format. Insert 'PZV' 'PZM' 'PZL'");	
		} else if (pizzaQuantity.matches(intRegex) == false) {
			throw new LogHandlerException("Make sure the quantity string is a numeric type");
		}
		
		LocalTime order = LocalTime.parse(orderTime);
		LocalTime delivery = LocalTime.parse(deliveryTime);
		int quantityToInt = Integer.parseInt(pizzaQuantity);
		
	
		Pizza returnThis = PizzaFactory.getPizza(pitsaCode, quantityToInt, order, delivery);
		return returnThis;
	}

}
