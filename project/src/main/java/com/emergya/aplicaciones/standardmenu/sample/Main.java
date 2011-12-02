package com.emergya.aplicaciones.standardmenu.sample;

import com.emergya.aplicaciones.standardmenu.IMenuFactory;
import com.emergya.aplicaciones.standardmenu.MenuException;
import com.emergya.aplicaciones.standardmenu.xmlmenu.XMLMenuFactory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IMenuFactory factory = new XMLMenuFactory();
		try {
			factory.getMenu("prueba");
		} catch (MenuException e) {
			e.printStackTrace();
		}
	}

}
