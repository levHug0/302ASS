package asgn2GUIs;

import java.awt.event.ActionEvent;

import java.text.DecimalFormat;

import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Raj Rosello and Levinard Hugo
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	

	private static final long serialVersionUID = 1764643302875814043L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private JPanel pnlDisplay, pnlTwo, pnlBtn, pnlFour, pnlFive;
	private JButton btnLoad, pizzaButton, customerButton, resetButton, calculateButton;
	private JTextArea pizzaTextArea;
	private JScrollPane scroll;
	private final static String newline = "\n";
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	//The file to be used
	private File file_opened;
	//Create a file chooser
	private final JFileChooser fc = new JFileChooser(System.getProperty("user.dir") + "\\logs");

	private PizzaRestaurant restaurant = new PizzaRestaurant();
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {

		super(title);
	}
	


	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
	    //Solution code uses different colours to highlight different panels 
	    pnlDisplay = createPanel(Color.WHITE);
	    pnlTwo = createPanel(Color.LIGHT_GRAY);
	    pnlBtn = createPanel(Color.LIGHT_GRAY);
	    pnlFour = createPanel(Color.LIGHT_GRAY);
	    pnlFive = createPanel(Color.LIGHT_GRAY);
	    
	    
	    
	    btnLoad = createButton("Open Log Files"); // creates the button
	    pizzaButton = createButton("Pizza Info");
	    customerButton = createButton("Customer Info");
	    resetButton = createButton("Reset");
	    calculateButton = createButton("Calculate");
	    resetButton.setEnabled(false);
	    pizzaButton.setEnabled(false);
	    customerButton.setEnabled(false);
	    calculateButton.setEnabled(false);

	    pizzaTextArea = createTextArea(); // creates the text box
	    layoutButtonPanel(); // puts the buttons on screen
	    

	    this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
	    this.getContentPane().add(pnlTwo,BorderLayout.NORTH);
	    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
	    this.getContentPane().add(pnlFour,BorderLayout.EAST);
	    this.getContentPane().add(pnlFive,BorderLayout.WEST);
	    scroll = new JScrollPane(pnlDisplay,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.getContentPane().add(scroll);
	    pnlDisplay.add(pizzaTextArea, BorderLayout.CENTER);
	    
	    repaint(); 
	    this.setVisible(true);
	}
	
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		jb.addActionListener(this);
		return jb; 
	}
	
	private JTextArea createTextArea() {
		JTextArea jta = new JTextArea(20,30);
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,12));
		jta.setBorder(BorderFactory.createEtchedBorder());
		return jta;
	}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlBtn.setLayout(layout);
	    
	    //add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    //Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    addToPanel(pnlBtn, btnLoad,constraints,0,0,2,1); 
	    addToPanel(pnlBtn, pizzaButton,constraints,3,0,2,1); 
	    addToPanel(pnlBtn, customerButton,constraints,5,0,2,1);
	    addToPanel(pnlBtn, calculateButton,constraints,7,0,2,1);
	    addToPanel(pnlBtn, resetButton,constraints,9,0,1,1);
	}
	
	/**
	*
	* A convenience method to add a component to given grid bag
	* layout locations. Code due to Cay Horstmann
	*
	* @param c the component to add
	* @param constraints the grid bag constraints to use
	* @param x the x grid position
	* @param y the y grid position
	* @param w the grid width of the component
	* @param h the grid height of the component
	*/
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	}
	
	
	
	
	@Override
	public void run() {
		createGUI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		
		if (src== btnLoad) {
			
			JButton btn = ((JButton) src);
			int returnVal = fc.showOpenDialog(this); // shows the txt files in the folder
			if(returnVal == JFileChooser.APPROVE_OPTION){
				   file_opened = fc.getSelectedFile();
				   String filename = file_opened.getName(); // gets name of file name
				   
				try {
					if (restaurant.processLog(filename)) {
						JOptionPane.showMessageDialog(this, "Successful", "Loading Log file",JOptionPane.INFORMATION_MESSAGE);
					}
						pizzaTextArea.setText(filename + " is Chosen"+ newline + newline + "Click the other buttons for more information..");
						pizzaButton.setEnabled(true);
						customerButton.setEnabled(true);
						calculateButton.setEnabled(true);
						resetButton.setEnabled(true);
						btnLoad.setEnabled(false);
						restaurant.processLog(filename); // loads the log file chosen
				} catch (CustomerException | PizzaException | LogHandlerException e1) {
				    JOptionPane.showMessageDialog(this, e1.getMessage(), "Loading Log file",JOptionPane.ERROR_MESSAGE); 
				} 
				  
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
				    pizzaTextArea.setText("Canceled");
			}//end else if
		}else if(src == pizzaButton){
			
			pizzaTextArea.setText("Pizza Order Information" + newline+ newline);
			for(int i = 0; i < restaurant.getNumPizzaOrders(); i++){
				try {
					pizzaTextArea.append("Pizza Type: " + restaurant.getPizzaByIndex(i).getPizzaType()+ newline);
					pizzaTextArea.append("Pizza Quantity: " + restaurant.getPizzaByIndex(i).getQuantity()+ newline);
					pizzaTextArea.append("Order Price: " + df2.format(restaurant.getPizzaByIndex(i).getOrderPrice())+ newline);
					pizzaTextArea.append("Order Cost: " + df2.format(restaurant.getPizzaByIndex(i).getOrderCost())+ newline);
					pizzaTextArea.append("Order Profit: " + df2.format(restaurant.getPizzaByIndex(i).getOrderProfit())+ newline);
					pizzaTextArea.append(newline);
				} catch (PizzaException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
				
			}//end for
		}else if(src == customerButton){
			
			pizzaTextArea.setText("Customer Information" + newline+ newline);
			// Customername, Mobilenumber, CustomerType, X and Y location, DeliveryDistance
			for( int i = 0; i < restaurant.getNumCustomerOrders(); i++){
				try {
					pizzaTextArea.append("Name: " + restaurant.getCustomerByIndex(i).getName() + newline);
					pizzaTextArea.append("Mobile Number: " + restaurant.getCustomerByIndex(i).getMobileNumber() + newline );
					pizzaTextArea.append("Delivery Type: " + restaurant.getCustomerByIndex(i).getCustomerType()+ newline );
					pizzaTextArea.append("X Location: " + restaurant.getCustomerByIndex(i).getLocationX()+newline );
					pizzaTextArea.append("Y Location: " + restaurant.getCustomerByIndex(i).getLocationY()+newline );
					pizzaTextArea.append("Delivery Distance(Unit = blocks): " + df2.format(restaurant.getCustomerByIndex(i).getDeliveryDistance()) + newline);
					pizzaTextArea.append(newline);
				} catch (CustomerException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
				
			} // end for
		}else if(src == calculateButton){//end if
			pizzaTextArea.setText("Calculations for total Profit and total Distance" + newline+ newline);
			pizzaTextArea.append("Total profit made:  " + df2.format(restaurant.getTotalProfit()) + newline);
			pizzaTextArea.append("Total distance travelled(Unit = blocks):  " + df2.format(restaurant.getTotalDeliveryDistance()) + newline);
			
		}else if(src == resetButton){// end if
			pizzaTextArea.setText("Select again");
			restaurant.resetDetails();
			btnLoad.setEnabled(true);
			pizzaButton.setEnabled(false);
			customerButton.setEnabled(false);
			calculateButton.setEnabled(false);
			resetButton.setEnabled(false);
		}// end else if
		
			
		
	}
		
		
	


}
