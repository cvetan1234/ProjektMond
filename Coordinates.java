package main;

public class Coordinates {
	
	Double xCoordinate;
	Double yCoordinate;
	
	Double latitude;
	Double longitude;
	
	Coordinates() {
	
	}
	
	Coordinates(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		xCoordinate = Double.valueOf(737.28 + longitude*4.096); // coordinate from longitude to JFrame coordinate
    	yCoordinate = Double.valueOf(323.1 - latitude*3.59); // coordinate from latitude to JFrame coordinate
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}	
	
	public double getXCoordinate() {
		return xCoordinate;
	}
	
	public double getYCoordinate() {
		return yCoordinate;
	}
}
