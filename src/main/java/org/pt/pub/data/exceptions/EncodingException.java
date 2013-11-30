package org.pt.pub.data.exceptions;

/**
 * Exception that should be thrown on the encoding of data
 * @author balhau
 *
 */
public class EncodingException extends Exception{
	public EncodingException(){
		super();
	}
	
	public EncodingException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public EncodingException(String exceptionMessage,Throwable cause){
		super(exceptionMessage,cause);
	}
	
	public EncodingException(String exceptionMessage,Throwable cause,boolean enableSupression, boolean writableStackTrace){
		super(exceptionMessage,cause,enableSupression,writableStackTrace);
	}
	
}
