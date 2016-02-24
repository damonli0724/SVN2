/**
 * 
 */
package com.saltedfish.exception;

/**
 * @author LKD
 * 短信异常
 */
public class SMSException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public SMSException(String message) {
		super(message);
	}

}
