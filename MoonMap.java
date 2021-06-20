package graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;  

public class MoonMap extends JComponent {
	
	private Coordinates c;
	static int diameter = 10;

	public void paint(Graphics g) {
    	try {
    		//System.out.println("Paint1");
            //BufferedImage originalImg = ImageIO.read(new File("moonmapERP.jpg"));
            //BufferedImage SubImg = coordinatesToRectangle(17, 59.1, originalImg);
            //File outputfile = new File("ImageCropped.jpg");
            //ImageIO.write(SubImg, "jpg", outputfile);
            //System.out.println("Cropped Image created successfully");
            //Graphics2D g2 = (Graphics2D) g; //casting
    		
    		// if (c == null) {
    		Image img = Toolkit.getDefaultToolkit().getImage("moonmap16ERP.jpg");
    		g.drawImage(img, 0, 0, null);
    		drawLines(g);
    		if (c != null) {
    			Graphics2D g2 = (Graphics2D) g;
    			Ellipse2D.Double o = new Ellipse2D.Double(c.latitude - (diameter/2), c.longitude - (diameter/2), diameter, diameter);
    	        g2.draw(o);
    	        g2.fill(o);
    	        // c = null;
    	        System.out.println("Circle repaint");
    		}
        	super.repaint();
        	//g.drawImage(SubImg, 0, 0, null);
            //int a = 10;
            //Ellipse2D.Double o = new Ellipse2D.Double(737.28 - (a/2), 323.1 - (a/2), a, a);
            //g2.draw(o);
            //g2.drawOval(-5, -5, 10, 10);
            //g2.drawOval(10, 10, 10, 10);
            //g2.fillOval(50, 50, 20, 20);4
            //findCoordinates(10, 10, g2, 10);
            //***findCoordinatesM(0, 0, g2, 10);
            //findCoordinatesM(100, 50, g2, 10);
            //***findCoordinatesM(19.92, -2.89, g2, 10); // Mons Huygens
            //***findCoordinatesM(5, 120.3, g2, 10); // Mons Dieter
            //***findCoordinatesM(27.66, -25.51, g2, 10); // Mons La Hire
            //***findCoordinatesM(45.41, 0.44, g2, 10); // Mons Blanc
            //***findCoordinatesM(17, 59.1, g2, 10); // Mare Crisium
            //findCoordinatesM(-13.4, -2.8, g2, 10); 
            //***findCoordinatesM(-12.7, -2.1, g2, 2);
            //***findCoordinatesM(-12.7, -1.6, g2, 2);
            //***findCoordinatesM(-12.6, -1.7, g2, 2);
            //***findCoordinatesM(-12.5, -1.9, g2, 2);
            //***findCoordinatesM(-12.9, -1.6, g2, 2);
            //findCoordinatesM(-20.08, 9.62, g2, 10);
            //findCoordinatesM(23.7, 15.4, g2, 10);
            //findCoordinatesM(-13.5, -29.8, g2, 10);
            //findCoordinatesM(-21.7, -49.5, g2, 10);
            //findCoordinatesM(-11.36, -43.31, g2, 10);
        }
  
        // Catch block to handle the exceptions
        catch (Exception e) {
  
            // Print the exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }
	
	public void setCoordinates(Coordinates c) {
		this.c = c;
	}
    
    public void findCoordinates(int X, int Y, Graphics2D g, int d) {
    	double nX = 4.096 * X;
    	double nY = 3.59 * Y;
        Ellipse2D.Double o = new Ellipse2D.Double(nX - (d/2), nY - (d/2), d, d);
        g.draw(o);
    }
    
    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
    	BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
    	return croppedImage;
    }
    
    public static BufferedImage numbersToRectangle (int num1, int num2, BufferedImage Img) {
    	int a = (num1-1)*256;
    	int b = (num2-1)*225;
    	BufferedImage SubImg = Img.getSubimage(a, b, 256, 225);
    	return SubImg;
    }
    
    public static BufferedImage coordinatesToRectangle (double kolkoNaSever, double kolkoNaIztok, BufferedImage Img) {
    	int num1;
    	int num2;
    	num1 = (int)Math.ceil((90 - kolkoNaSever)/10);
    	num2 = (int)Math.ceil((kolkoNaIztok + 180)/10);
    	System.out.println("горе-долу: " + num1);
    	System.out.println("ляво-дясно: " + num2);
    	return numbersToRectangle(num2, num1, Img);
    }
    
    public void drawLines (Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	for (int numberOfLines = 0; numberOfLines < 19; numberOfLines++) {
        	Shape l = new Line2D.Double(1, 35.9*numberOfLines, 1475, 35.9*numberOfLines);
        	g2.draw(l);
        }
        for (int numberOfLines = 0; numberOfLines < 37; numberOfLines++) {
        	Shape l = new Line2D.Double(40.96*numberOfLines, 1, 40.96*numberOfLines, 646);
        	g2.draw(l);
        }
    }

}
