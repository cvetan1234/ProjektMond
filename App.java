package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	
	public static void main(String[] args) {
		ArrayList <Coordinates> coordinatesList = new ArrayList<Coordinates>(); 
		
		JFrame f = new JFrame();   // creates a JFrame
        f.setTitle("Moon Map");     //sets the title
        //f.setSize(1600, 1000);   //sets the size
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // sets the default close operation
       
        f.setLayout(null); // using no layout managers
		//System.out.println("Test 1");
        
		
		MoonMap moonmap = new MoonMap();    //indirectly calls the paint method
		//System.out.println("Test 2");
		moonmap.setBounds(30, 1, 1475, 647);
		//System.out.println("Test 3");
		// c.fill = GridBagConstraints.HORIZONTAL;
	    f.add(moonmap);    //adds the paint method onto the JFrame

		
        //System.out.println("Test 4");
        
		//Original Image Dimension: 1475x647
		
	    JLabel labelLatitude = new JLabel("Ширина:");
	    labelLatitude.setBounds(30, 668, 100, 30);
	    labelLatitude.setFont(labelLatitude.getFont().deriveFont(18f));
	    
	    JLabel labelLongitude = new JLabel("Дължина:");
	    labelLongitude.setBounds(30, 708, 100, 30);
	    labelLongitude.setFont(labelLongitude.getFont().deriveFont(18f));
	    
        JTextArea areaLatitude = new JTextArea("");
        areaLatitude.setBounds(140, 668, 100, 30);
        areaLatitude.setFont(areaLatitude.getFont().deriveFont(18f));
        
        JTextArea areaLongitude = new JTextArea("");
        areaLongitude.setBounds(140, 708, 100, 30);
        areaLongitude.setFont(areaLongitude.getFont().deriveFont(18f));
        
		JButton showOnTheMap = new JButton("Show on the map"); //creating instance of JButton
		showOnTheMap.setBounds(270, 668, 200, 70); 
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
					Double latitude = validateLatitude(areaLatitude.getText());
					Double longitude = validateLongitude(areaLongitude.getText());
					Coordinates c = new Coordinates (latitude, longitude);
					moonmap.setCoordinates(c);
					coordinatesList.add(c);
				} catch (InvalidDataException d){
					d.printStackTrace();
					moonmap.setCoordinates(null);
					areaLatitude.setText("");
					areaLongitude.setText("");
					JOptionPane.showMessageDialog(f, "Invalid data");
				}
			}
		});
		
		JButton clear = new JButton("Clear"); //creating instance of JButton
		clear.setBounds(490, 668, 200, 70); 
		// goToCoordinates.setBackground(new Color(0,153,0));
		clear.setFont(new Font("Arial", Font.ITALIC, 15));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				moonmap.setCoordinates(null);
				areaLatitude.setText("");
				areaLongitude.setText("");
			}
		});
		
		JButton save = new JButton("Save"); //creating instance of JButton
		save.setBounds(710, 668, 200, 70); 
		// goToCoordinates.setBackground(new Color(0,153,0));
		save.setFont(new Font("Arial", Font.ITALIC, 15));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				saveToFile(coordinatesList);
				coordinatesList.clear();
			}
		});
		
		/*JButton showDetails = new JButton("Show detailed map"); //creating instance of JButton
		showDetails.setBounds(710, 668, 200, 70); 
		// goToCoordinates.setBackground(new Color(0,153,0));
		showDetails.setFont(new Font("Arial", Font.ITALIC, 15));
		showDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(!moonmap.areCoordinatesSelected()) {
					JOptionPane.showMessageDialog(f, "Point is not selected on the map!");
				} else {
					System.out.println("ASKJJKAS");
					CropImage dialog = new CropImage(f, moonmap.getCoordinates());
					dialog.setVisible(true);
				}
			}
		});
		*/
		
		JCheckBox grid = new JCheckBox("Grid");
		grid.setSelected(true);
		grid.setBounds(930, 668, 200, 70);
		grid.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) { 
            	if (e.getStateChange()==1) {
            		moonmap.setGridCheck(true);
            	} else {
            		moonmap.setGridCheck(false);
            	}
            }    
        });    
		
		
		f.add(labelLatitude);
		f.add(labelLongitude);
		f.add(areaLatitude);
		f.add(areaLongitude);
		f.add(showOnTheMap);
		f.add(clear);
		f.add(save);
		//f.add(showDetails);
		f.add(grid);
		
		
		//System.out.println("Test 5");
        f.setVisible(true);
		
        //System.out.println("Test 6");
		
	}
	
	private static void saveToFile(ArrayList list) {
		try {
			FileWriter myWriter = new FileWriter("Coordinates.txt");
			ListIterator <Coordinates> i = list.listIterator();
		    while (i.hasNext()) {
		    	Coordinates c = (Coordinates) i.next();
		    	myWriter.write("Latitude: " + c.getLatitude() + " Longitude: " + c.getLongitude() + "\n");
		    }
		    myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Double validateLatitude(String data) throws InvalidDataException {
		Double value;
		
		try {
			value = Double.parseDouble(data);
		} catch (NumberFormatException e) {
			throw new InvalidDataException(data);
		}
		
		if (value > 90 || value < -90) {
			throw new InvalidDataException(data);
		}
		
		return value;
	}
	
	private static Double validateLongitude(String data) throws InvalidDataException {
		Double value;
		
		try {
			value = Double.parseDouble(data);
		} catch (NumberFormatException e) {
			throw new InvalidDataException(data);
		}
		
		if (value > 180 || value < -180) {
			throw new InvalidDataException(data);
		}
		
		return value;
	}
}
