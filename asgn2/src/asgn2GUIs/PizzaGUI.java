package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;

import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
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
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	

	private static final long serialVersionUID = 1764643302875814043L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	
	private JPanel pnlDisplay;
	private JPanel pnlTwo;
	private JPanel pnlBtn;
	private JPanel pnlFour;
	private JPanel pnlFive;
	
	private JButton btnLoad;

	private JTextArea pizzaTextArea;
	
	//Create a file chooser
	private final JFileChooser fc = new JFileChooser();


	private PizzaRestaurant restaurant;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		// TO DO
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

	    pizzaTextArea = createTextArea(); // creates the text box
	    layoutButtonPanel(); // puts the buttons on screen
	    
	    pnlDisplay.add(pizzaTextArea, BorderLayout.CENTER);
	    
	    this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
	    this.getContentPane().add(pnlTwo,BorderLayout.NORTH);
	    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
	    this.getContentPane().add(pnlFour,BorderLayout.EAST);
	    this.getContentPane().add(pnlFive,BorderLayout.WEST);
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
		JTextArea jta = new JTextArea(); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,24));
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
		// TO DO
		createGUI();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src=e.getSource();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir" + logs)));
		int returnVal = fc.showOpenDialog(this);
		
		
			//JButton btn = ((JButton) src);
		if(returnVal == JFileChooser.APPROVE_OPTION){
		   File file = fc.getSelectedFile();
		   String filename = file.getAbsolutePath();
		}else if(returnVal == JFileChooser.CANCEL_OPTION){
		    	
		    }
			
		}
		
		
	


}
