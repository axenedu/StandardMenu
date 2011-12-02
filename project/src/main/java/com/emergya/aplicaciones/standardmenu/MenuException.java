package com.emergya.aplicaciones.standardmenu;

import java.io.Serializable;

/**
 * Menu exception 
 * @author frodriguez
 *
 */
public class MenuException extends Exception implements Serializable{

	/**
	 * UID Serializacion
	 */
	private static final long serialVersionUID = -8906326297536840578L;

	/**
	 * Internal level error
	 */
	private String msg;
	
	/**
	 * Error code
	 */
	private Integer codErr;
	
	/**
	 * Error ticket that is a international key of the message
	 */
	private String tickect;
	
	/**
	 * Original exception that causes the error
	 */
	private Exception sourceException;
	
	/**
	 * Constructor of MenuExcetpion
	 * @param sourceExcetpion Exception that launches MenuException
	 * @param msg internal message of error
	 * @param codErr error code
	 * @param ticket ticket code associated with the message
	 */
	public MenuException(Exception sourceException, String msg, Integer codErr, String ticket) {
		this.sourceException = sourceException;
		this.msg = msg;
		this.codErr = codErr;
		this.tickect = ticket;
	}

	/**
	 * @return message msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg internal message of error
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return error code
	 */
	public Integer getCodErr() {
		return codErr;
	}

	/**
	 * @param codErr set error code
	 */
	public void setCodErr(Integer codErr) {
		this.codErr = codErr;
	}

	/**
	 * @return ticket id
	 */
	public String getTickect() {
		return this.tickect;
	}

	/**
	 * @param tickect set ticket id
	 */
	public void setTickect(String tickect) {
		this.tickect = tickect;
	}

	/**
	 * @return sourceExcepcion
	 */
	public Exception getSourceException() {
		return sourceException;
	}

	/**
	 * @param sourceException set sourceException
	 */
	public void setSourceException(Exception sourceException) {
		this.sourceException = sourceException;
	}

	/**
	 * 
	 */
	public String toString(){
		String res = new String();
		if(msg != null)
			res += msg;
		if(codErr != null)
			res += !res.isEmpty() ? " - " + codErr : codErr;
		
		if(sourceException != null)
			res += !res.isEmpty() ? "\n" + sourceException : sourceException;
		
		return res;
	}

}
