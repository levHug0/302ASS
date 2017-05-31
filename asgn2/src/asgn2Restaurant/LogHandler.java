package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.File;
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
 * @author Levinard Hugo (Person A) and Raj Rosello (Person B)
 *
 */
public class LogHandler {
	static BufferedReader br;
	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		File file = new File("./logs/" + filename);
		
		if (file.exists() == false) {
	    	throw new LogHandlerException("File doesn't exist.");
	    } 
		
		ArrayList<Customer> returnCus = new ArrayList<Customer>();
		FileReader fl = null;				// main one
		FileReader lineCounter = null;		// used for counting lines
		
		try {
			lineCounter = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		LineNumberReader lnr = new LineNumberReader(lineCounter);
		try {
			lnr.skip(Long.MAX_VALUE);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		int lineReader = lnr.getLineNumber() + 1;		// +1 because getLineNumber starts from 0

		for (int i = 1; i <= lineReader; i++) {
			try {
				fl = new FileReader(file);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			br = new BufferedReader(fl);
			String eye = Integer.toString(i);
			returnCus.add(createCustomer(eye));
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return returnCus;
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
		File file = new File("./logs/" + filename);		// setting the relative path
		
		if (file.exists() == false) {
	    	throw new LogHandlerException("File doesn't exist.");
	    } 
		
		ArrayList<Pizza> returnPizz = new ArrayList<Pizza>();
		FileReader fl = null;				// main one
		FileReader lineCounter = null;		// used for counting lines
		
		try {
			lineCounter = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		LineNumberReader lnr = new LineNumberReader(lineCounter);
		try {
			lnr.skip(Long.MAX_VALUE);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		int lineReader = lnr.getLineNumber() + 1;		// +1 because getLineNumber starts from 0

		for (int i = 1; i <= lineReader; i++) {
			try {
				fl = new FileReader(file);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			br = new BufferedReader(fl);
			String eye = Integer.toString(i);
			returnPizz.add(createPizza(eye));
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return returnPizz;
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
<<<<<<< HEAD

		String nameRegex = "[a-zA-Z][a-zA-Z' ]{0,19}";
=======
		String nameRegex = "^[a-zA-Z][a-zA-Z' ]{0,19}$";
>>>>>>> 5d5c5375ee947608063a037c0f6746c2b77439fb
		String mobileNumberRegex = "0[0-9]{9}";
		String codeRegex1 = "PUC";
		String codeRegex2 = "DNC";
		String codeRegex3 = "DVC";
		int lineToInt;
		
		if (line.matches(intRegex) == false) {
			throw new LogHandlerException("Parameter needs to be a numeric string");
		} else if ((lineToInt = Integer.parseInt(line)) < 1) {
			throw new LogHandlerException("Line number cannot be a negative or a zero");
		}
		
		String myLine = null;
		for (int i = 0;i < lineToInt; i++) {
			try {
				myLine = br.readLine();		
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		String[] compareArray = myLine.split(",");
		String name = compareArray[2];
		String mobileNumber = compareArray[3];
		String customercode = compareArray[4];
		
				
		if(name.matches(nameRegex) == false){
			throw new LogHandlerException("Name Format should be maximum 20 characters long minimum of 1 letter and should not have only whitespace");
		} else if (mobileNumber.matches(mobileNumberRegex) == false){
			throw new LogHandlerException("Mobile number should start with a 0 and have 10 numbers");
		} else if ((customercode.matches(codeRegex1) || customercode.matches(codeRegex2) || customercode.matches(codeRegex3)) == false){
			throw new LogHandlerException("Customer code should be PUC, DNC or DVC");
		}	
			
		int locationX = Integer.parseInt(compareArray[5]);
		int locationY = Integer.parseInt(compareArray[6]);
		
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
		// These are used for checking if they can be parsed into a specific data type
		String intRegex = "[0-9]+";
		String timeRegex = "(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)";
		String codeRegex = "PZ[VML]";
		
		int lineToInt;
		if (line.matches(intRegex) == false) {
			throw new LogHandlerException("Parameter needs to be a numeric string");
		} else if ((lineToInt = Integer.parseInt(line)) < 1) {
			throw new LogHandlerException("Line number needs to be atleast '1' or more");	
		}
		
		String thisLine = null;
		for (int i = 0;i < lineToInt; i++) {
			try {
				thisLine = br.readLine();		
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		String[] pizzArr = thisLine.split(",");
		
		// Below checks if each data can be parsed into their specific type
		if (pizzArr[0].matches(timeRegex) == false) {
			throw new LogHandlerException("Order Time format incorrect. Correct format is HH:MM:SS");	
		} else if (pizzArr[1].matches(timeRegex) == false) {
			throw new LogHandlerException("Delivery Time format incorrect. Correct format is HH:MM:SS");	
		} else if (pizzArr[7].matches(codeRegex) == false) {
			throw new LogHandlerException("Incorrect pizzaCode format. Insert 'PZV' 'PZM' 'PZL'");	
		} else if (pizzArr[8].matches(intRegex) == false) {
			throw new LogHandlerException("Make sure the quantity string is numeric");
		}
		
		// Below parses the information 
		LocalTime orderTime = LocalTime.parse(pizzArr[0]);
		LocalTime deliveryTime = LocalTime.parse(pizzArr[1]);
		int quantity = Integer.parseInt(pizzArr[8]);
		
		Pizza thisPizza = PizzaFactory.getPizza(pizzArr[7], quantity, orderTime, deliveryTime);
		return thisPizza;
	}

}
