package main;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;  
import resources.ResourceLoader;

public class MoonMap extends JComponent {
	
	private Coordinates c;
	private int diameter = 10;
	private boolean gridCheck = true;
	
	public void paintComponent(Graphics g) {    	
		super.paintComponent(g);
		Image img = ResourceLoader.loadImage("moonmap16ERP.jpg");
		g.drawImage(img, 0, 0, null);
    		
		if (gridCheck) {
			drawLines(g);
		}
    		
		if (c != null) {
			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D.Double o = new Ellipse2D.Double(c.getXCoordinate() - (diameter/2), c.getYCoordinate() - (diameter/2), diameter, diameter);
			g2.setPaint(Color.RED);
			g2.draw(o);
			g2.fill(o);
			g2.setPaint(Color.BLACK);
			//System.out.println("Circle repaint");
		}
		repaint();
    }
	
	public void setCoordinates(Coordinates c) {
		this.c = c;
	}
	
	public Coordinates getCoordinates() {
		return c;
	}
	
	public boolean areCoordinatesSelected() {
		if (c == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setGridCheck(boolean gridCheck) {
		this.gridCheck = gridCheck;
	}
	
    private void drawLines (Graphics g) {
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
