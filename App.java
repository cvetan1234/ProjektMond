package graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
	    JLabel latitudeL = new JLabel("Ширина:");
	    latitudeL.setBounds(30, 668, 100, 30);
	    latitudeL.setFont(latitudeL.getFont().deriveFont(18f));
	    
	    JLabel longitudeL = new JLabel("Дължина:");
	    longitudeL.setBounds(30, 708, 100, 30);
	    longitudeL.setFont(longitudeL.getFont().deriveFont(18f));
	    
        JTextArea areaLatitude = new JTextArea("");
        areaLatitude.setBounds(140, 668, 100, 30);
        areaLatitude.setFont(areaLatitude.getFont().deriveFont(18f));
        
        JTextArea areaLongitude = new JTextArea("");
        areaLongitude.setBounds(140, 708, 100, 30);
        areaLongitude.setFont(areaLongitude.getFont().deriveFont(18f));
        
		JButton goToCoordinates = new JButton("Show on the map"); //creating instance of JButton
		goToCoordinates.setBounds(270, 668, 200, 70); 
		// goToCoordinates.setBackground(new Color(0,153,0));
		goToCoordinates.setFont(new Font("Arial", Font.ITALIC, 15));
		goToCoordinates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				Double coordLatitude = Double.parseDouble(areaLatitude.getText());
				Double coordLongitude = Double.parseDouble(areaLongitude.getText());
				//System.out.println(longitude + " " + latitude);
				Coordinates c = findCoordinatesM(coordLatitude, coordLongitude);
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
			}
		});
		
		f.add(latitudeL);
		f.add(longitudeL);
		f.add(goToCoordinates);
		f.add(clear);
		f.add(areaLatitude);
		f.add(areaLongitude);
		
		
		//System.out.println("Test 5");
        f.setVisible(true);
		
        //System.out.println("Test 6");
		
	}
	
	public static Coordinates findCoordinatesM (double kolkoNaSever, double kolkoNaIztok) {  
    	Double latitude = Double.valueOf(737.28 + kolkoNaIztok*4.096);
    	Double longitude = Double.valueOf(323.1 - kolkoNaSever*3.59);
    	return new Coordinates (latitude, longitude);
    	//Ellipse2D.Double o = new Ellipse2D.Double(Y - (d/2), X - (d/2), d, d);
        //g.draw(o);
        //g.fill(o);
    }

}
