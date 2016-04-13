package com.adam.Interest.javaknowledge.Exceptions.custom.exception.checked;

import java.io.IOException;

public class CustomIoException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8523299433276532846L;
	
	public CustomIoException(String msg){
		super(msg);
	}
	
	public CustomIoException(){
		super();
	}

}
