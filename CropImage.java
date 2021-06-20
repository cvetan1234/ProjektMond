package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CropImage extends JDialog{
	BufferedImage subImage;
	
	public CropImage(JFrame f, Coordinates c) {
		super(f);
		setTitle("Detailed map");
		//System.out.println("Hallo1");
		try {
			BufferedImage originalImg = ImageIO.read(new File("moonmapERP.jpg"));
			// System.out.println("Hallo2");
		    subImage = coordinatesToRectangle(c.getLatitude(), c.getLongitude(), originalImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("Hallo3");
		JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(subImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        add(panel);
	}
    
    public BufferedImage numbersToRectangle (int num1, int num2, BufferedImage Img) {
    	int a = (num1-1)*256;
    	int b = (num2-1)*225;
    	BufferedImage SubImg = Img.getSubimage(a, b, 256, 225);
    	return SubImg;
    }
    
    public BufferedImage coordinatesToRectangle (Double kolkoNaSever, Double kolkoNaIztok, BufferedImage Img) {
    	int num1;
    	int num2;
    	num1 = (int)Math.ceil((90 - kolkoNaSever.doubleValue())/10);
    	num2 = (int)Math.ceil((kolkoNaIztok.doubleValue() + 180)/10);
    	System.out.println("горе-долу: " + num1);
    	System.out.println("ляво-дясно: " + num2);
    	return numbersToRectangle(num2, num1, Img);
    } 
}

