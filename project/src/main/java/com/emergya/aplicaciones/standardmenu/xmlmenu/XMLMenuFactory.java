package com.emergya.aplicaciones.standardmenu.xmlmenu;

import com.emergya.aplicaciones.standardmenu.IMenu;
import com.emergya.aplicaciones.standardmenu.IMenuFactory;
import com.emergya.aplicaciones.standardmenu.MenuException;

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

public class XMLMenuFactory implements IMenuFactory {
	
	private static final String SEPARATOR = "/";
	
	/**
	 * Gets Imenu from xml name
	 * @param nombre, xml name
	 * @return IMenu, generated menu
	 */
	public IMenu getMenu(String nombre) throws MenuException {
		
		// Xml path
		String path = getPathByName(nombre);
		
		IMenu menu = null;
		// Parser to read 
		XmlParser parser = new XmlParser();
		
		menu = parser.createMenu(path);
		
		return menu;
	}
	
	/**
	 * Gets a path of xml from name of file
	 *
	 * @param description the new descripction
	 */
	private String getPathByName(String nombre){
		
		String path = null;
		
		String fileName = nombre + "_menu.xml";
		String full_classPath = System.getProperty("java.class.path");
		String[] separate_classPath = full_classPath.split(":");
		String classPath = separate_classPath[0];
		path = classPath + SEPARATOR + fileName;
		
		return path;
	}
}
