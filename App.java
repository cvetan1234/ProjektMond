package main;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class App {
	
	static FileWriter myWriter;
	
	public static void main(String[] args) {
		ArrayList <Coordinates> coordinatesList = new ArrayList<Coordinates>(); // ArrayList to store the coordinates
		try {
			myWriter = new FileWriter("Coordinates.txt");
		} catch (IOException e){
			e.printStackTrace();
		}
		JFrame f = new JFrame();   // creates a JFrame
        f.setTitle("Moon Map");     //sets the title
        //f.setSize(1600, 1000);   //sets the size
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); //sets fullscreen
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // sets the default close operation
       
        f.setLayout(null); // using no layout managers
		
		MoonMap moonmap = new MoonMap();    //indirectly calls the paint method
		moonmap.setBounds(30, 1, 1475, 647); // setting the position and size of MoonMap object (image)
	    f.add(moonmap);    //adds the paint method onto the JFrame
		
	    JLabel labelLatitude = new JLabel("Latitude (-90 to 90):"); // creating instance of JLabel
	    labelLatitude.setBounds(30, 668, 250, 30); // setting labels position and size
	    labelLatitude.setFont(labelLatitude.getFont().deriveFont(18f));
	    
	    JLabel labelLongitude = new JLabel("Longitude (-180 to 180):"); // creating instance of JLabel
	    labelLongitude.setBounds(30, 708, 250, 30); // setting labels position and size
	    labelLongitude.setFont(labelLongitude.getFont().deriveFont(18f));
	    
        JTextArea areaLatitude = new JTextArea(""); // creating instance of JTextArea
        areaLatitude.setBounds(280, 668, 100, 30); // setting areas position and size
        areaLatitude.setFont(areaLatitude.getFont().deriveFont(18f)); 
        
        JTextArea areaLongitude = new JTextArea(""); // creating instance of JTextArea
        areaLongitude.setBounds(280, 708, 100, 30); // setting areas position and size
        areaLongitude.setFont(areaLongitude.getFont().deriveFont(18f));
        
		JButton showOnTheMap = new JButton("Show on the map"); //creating instance of JButton
		showOnTheMap.setBounds(410, 668, 200, 70); // setting buttons position and size
		// goToCoordinates.setBackground(new Color(0,153,0));
		showOnTheMap.setFont(new Font("Arial", Font.ITALIC, 15));
		showOnTheMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				//System.out.println(areaLongitude.getText());
				if (areaLongitude.getText().isEmpty() || areaLatitude.getText().isEmpty()) {
					JOptionPane.showMessageDialog(f, "Coordinate/s are not selected!"); 
					return;
				}
				try {
					Double latitude = validateLatitude(areaLatitude.getText()); // gets the latitude from the first JTextArea
					Double longitude = validateLongitude(areaLongitude.getText()); // gets the Longitude from the second JTextArea
					Coordinates c = new Coordinates (latitude, longitude);
					moonmap.setCoordinates(c); // sets the coordinates to the moonmap 
					coordinatesList.add(c); // adds the coordinates to the ArrayList
				} catch (InvalidDataException d){
					d.printStackTrace();
					moonmap.setCoordinates(null); // sets the coordinates of the moonmap to be null
					areaLatitude.setText(""); // clears the value from the first JTextArea
					areaLongitude.setText(""); // clears the value from the second JTextArea
					JOptionPane.showMessageDialog(f, "Invalid data"); // shows message: Invalid data 
				}
			}
		});
		
		JButton clear = new JButton("Clear"); //creating instance of JButton
		clear.setBounds(630, 668, 200, 70); // setting buttons position and size
		// goToCoordinates.setBackground(new Color(0,153,0));
		clear.setFont(new Font("Arial", Font.ITALIC, 15));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				moonmap.setCoordinates(null); // sets the coordinates of the moonmap to be null
				areaLatitude.setText(""); // clears the value from the first JTextArea
				areaLongitude.setText(""); // clears the value from the second JTextArea
			}
		});
		
		JButton save = new JButton("Save"); //creating instance of JButton
		save.setBounds(850, 668, 200, 70);  // setting buttons position and size
		// goToCoordinates.setBackground(new Color(0,153,0));
		save.setFont(new Font("Arial", Font.ITALIC, 15));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				saveToFile(coordinatesList); // saves all coordinates to the file
				coordinatesList.clear(); // clears the list
			}
		});
		
		JButton open = new JButton("Open"); //creating instance of JButton
		open.setBounds(1070, 668, 200, 70); 
		// goToCoordinates.setBackground(new Color(0,153,0));
		open.setFont(new Font("Arial", Font.ITALIC, 15));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				File file = new File("Coordinates.txt");
		        
		        //first check if Desktop is supported by Platform or not
		        if(!Desktop.isDesktopSupported()){
		            System.out.println("Desktop is not supported");
		            return;
		        }
		        
		        Desktop desktop = Desktop.getDesktop();
		        if(file.exists()) {
		        	try{
		        		desktop.open(file);
		        	} catch (IOException k) {
		        		k.printStackTrace();
		        	}
		        }
			}
		});
		
		JCheckBox seeAllC = new JCheckBox("Show all coordinates");
		seeAllC.setSelected(false);
		seeAllC.setBounds(1290, 660, 200, 30);
		seeAllC.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) { 
            	if (e.getStateChange()==1) {
            		moonmap.printAllEllipses = true;
            	} else {
            		moonmap.printAllEllipses = false;
            	}
            }    
        });  
		
		JCheckBox grid = new JCheckBox("Grid"); // creating instance of JCheckBox 
		grid.setSelected(true); // stay selected
		grid.setBounds(1290, 700, 200, 30); // setting checkboxes position and size
		grid.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) { 
            	if (e.getStateChange()==1) {
            		moonmap.setGridCheck(true); // if selected set the boolean (setGridCheck) to true -> paint the grid
            	} else {
            		moonmap.setGridCheck(false); // if not selected set the boolean (setGridCheck) to false -> dont paint the grid
            	}
            }    
        });    
		
		//Add all elements to the frame
		f.add(labelLatitude);
		f.add(labelLongitude);
		f.add(areaLatitude);
		f.add(areaLongitude);
		f.add(showOnTheMap);
		f.add(clear);
		f.add(save);
		f.add(open);
		//f.add(showDetails);
		f.add(seeAllC);
		f.add(grid);
	
        f.setVisible(true); // set the frame visible
		
	}
	
	private static void saveToFile(ArrayList list) {
		try {
			ListIterator <Coordinates> i = list.listIterator();
		    while (i.hasNext()) {
		    	Coordinates c = (Coordinates) i.next();
		    	myWriter.write("Latitude: " + c.getLatitude() + " Longitude: " + c.getLongitude() + "\n");
		    }
		    myWriter.flush();
		    //myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Double validateLatitude(String data) throws InvalidDataException {
		Double value;
		
		try {
			value = Double.parseDouble(data); // parse to Double
		} catch (NumberFormatException e) { // if the value is not a number
			throw new InvalidDataException(data); // throw exception (InvalidDataException)
		}
		
		if (value > 90 || value < -90) { // if value is out of range
			throw new InvalidDataException(data); // throw exception (InvalidDataException)
		}
		
		return value;
	}
	
	private static Double validateLongitude(String data) throws InvalidDataException {
		Double value;
		
		try {
			value = Double.parseDouble(data); // parse to Double
		} catch (NumberFormatException e) { // if the value is not a number 
			throw new InvalidDataException(data); // throw exception (InvalidDataException)
		}
		
		if (value > 180 || value < -180) { // if value is out of range
			throw new InvalidDataException(data); // throw exception (InvalidDataException) 
		}
		
		return value;
	}
}
