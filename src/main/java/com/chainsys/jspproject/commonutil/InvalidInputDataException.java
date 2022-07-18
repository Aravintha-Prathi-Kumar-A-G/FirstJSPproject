package com.chainsys.jspproject.commonutil;
/**
 *  @author arav3116 chainsys
 *  sub - miniproject
 *  created on : 17-05-2022
 * 
 */

	public class InvalidInputDataException extends Exception{ 
	    public InvalidInputDataException() {
	    	super("The Input Data is not valid ");
	    }
	    public InvalidInputDataException(String message) {
	    	super(message);
	    }

}
