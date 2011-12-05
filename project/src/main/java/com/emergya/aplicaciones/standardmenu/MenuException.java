package com.emergya.aplicaciones.standardmenu;

/**
 * Copyright (C) 2011, Emergya (http://www.emergya.es)
 *
 * @author <a href="mailto:eserrano@emergya.com">Eduardo Serrano Luque</a>
 * @author <a href="mailto:jsoler@emergya.com">Jaime Soler</a>
 * @author <a href="mailto:jariera@emergya.com">José Alfonso Riera</a>
 * @author <a href="mailto:frodriguez@emergya.com">Francisco Rodríguez Mudarra</a>
 *
 * This file is Component StandardMenu
 *
 * This software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301  USA
 *
 * As a special exception, if you link this library with other files to
 * produce an executable, this library does not by itself cause the
 * resulting executable to be covered by the GNU General Public License.
 * This exception does not however invalidate any other reasons why the
 * executable file might be covered by the GNU General Public License.
 */

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
