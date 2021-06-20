package graphics;

public class Coordinates {
	
	Double latitude;
	Double longitude;
	
	Coordinates() {
		
	}
	
	Coordinates(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}	
}
