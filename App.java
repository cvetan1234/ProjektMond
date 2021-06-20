package graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class App {
	
	public static void main(String[] args) {
		JFrame f = new JFrame();   // creates a JFrame
        f.setTitle("Example1234");     //sets the title
        //f.setSize(1600, 1000);   //sets the size
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // sets the default close operation
       
        // GridBagLayout layout = new GridBagLayout();
        // GridBagConstraints c = new GridBagConstraints();
        
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
				System.out.println(areaLongitude.getText());
				if (areaLongitude.getText().isEmpty() || areaLatitude.getText().isEmpty()) {
					JOptionPane.showMessageDialog(f, "Coordinate/s are not selected!"); 
					return;
				}
				Double coordLatitude = Double.parseDouble(areaLatitude.getText());
				Double coordLongitude = Double.parseDouble(areaLongitude.getText());
				//System.out.println(longitude + " " + latitude);
				Coordinates c = moonmap.findCoordinates(coordLatitude, coordLongitude);
				moonmap.setCoordinates(c);
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
		grid.setBounds(710, 668, 200, 70);
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
		//f.add(showDetails);
		f.add(grid);
		
		
		//System.out.println("Test 5");
        f.setVisible(true);
		
        //System.out.println("Test 6");
		
	}

}
