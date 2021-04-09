package com.carlosrey.springboot.backend.apirest.exception;

public class BookingException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private final String code;
	
	private final int responseCode;


	public BookingException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}
	

	public String getCode() {
		return code;
	}

	public int getResponseCode() {
		return responseCode;
	}


}
