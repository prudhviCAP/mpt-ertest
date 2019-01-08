package com.capgemini.xyz.exception;

public class XYZException extends Exception{

	public XYZException(String message) {
		super(message);
		System.out.println("xyz exception caught here");
	}

	
}
