package main;

public class InvalidDataException extends Exception {
	String data;
	
	public InvalidDataException() {
		
	}
	
	public InvalidDataException(String data) {
		super("Invalid latitude or longitude: " + data);
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
}