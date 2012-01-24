package com.emergya.aplicaciones.standardmenu.sample;

import java.io.File;

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
			
			String path = null;
			String nombre = "menu1";
			String fileName = nombre + "_menu.xml";
			String full_classPath = System.getProperty("java.class.path");
			String[] separate_classPath = full_classPath.split(":");
			String classPath = separate_classPath[0];
			path = classPath + File.separatorChar+ fileName;
			
			factory.getMenu(path);
			
		} catch (MenuException e) {
			e.printStackTrace();
		}
	}

}
