package com.emergya.aplicaciones.standardmenu;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.emergya.aplicaciones.standardmenu.xmlmenu.XMLMenuFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
   
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp(){
    	
    	System.out.println("Entrando en el test menuXML\n");
    	
    	IMenuFactory factoria = new XMLMenuFactory();
    	try {
			IMenu menu1 = factoria.getMenu("menu1");		
			printMenu(menu1);
		} catch (MenuException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Saliendo del test menuXML");
    }
    
    /**
     * Print a IMenu
     * @param menu
     */
    public void printMenu(IMenu menu){
    	
    	System.out.println("<-Printing menu structure->\n");
    	
    	imprimeMenuStructure(menu);
    	
    	System.out.println("\n<-Printing info main menu->\n");
    	System.out.println("Menu name: " + menu.getName());
    	
    	if(menu.getDescription() != null){    		
    		System.out.println("Menu description: " +menu.getDescription());
    	}
    	
    	Collection<String> profiles = menu.getProfiles();
    	if(!profiles.isEmpty()){    		
    		String proString = "";
    		for(String profile : profiles){
    			proString += "[" + profile + "]";
    		}
    		System.out.println("Profiles: " + proString);
    	}
    	
    	String globalParams = "";
    	Iterator<Map.Entry<String, String>> itGParam = menu.getGlobalParamskeys();
    	while(itGParam.hasNext()){
    		Map.Entry<String, String> gParamEntry = itGParam.next();
    		globalParams += "[" + gParamEntry.getKey() + "," + gParamEntry.getValue() + "]";
    	}
    	if(globalParams != ""){    		
    		System.out.println("Global Parameters: " + globalParams );
    	}
    	
    	System.out.println("Base url: " + menu.getBaseUrl());
    	
    	System.out.println("\n<-Printing info children->\n");
    	Collection<INodeMenu> children = menu.getChildren();
    	printChildren(children);
    }
    
    /**
	 * Shows menu structure 
	 *
	 * @param Imenu menu
	 */
	public void imprimeMenuStructure(IMenu menu){
		
		Collection<INodeMenu> nodosNivelCero = menu.getChildren();
		
		for (INodeMenu iNodeMenu : nodosNivelCero) {
			int deep = 0;
			System.out.println(iNodeMenu.getText());
			
			Collection<INodeMenu> hijos = iNodeMenu.getChildren();
			for (INodeMenu iNodeMenuHijo : hijos) {
				imprimeNodoStructure(deep, iNodeMenuHijo);
			}
			
		}
	}

	/**
	 * Print a node structure
	 * @param deep node	
	 * @param nodo, node 
	 */
	private void imprimeNodoStructure(int deep, INodeMenu nodo){
		
		deep ++;
		
		String tab="";
		for(int i=0; i<=deep; i++){
			tab+="\t";
		}
		
		System.out.println(tab + nodo.getText());
		
		Collection<INodeMenu> hijos = nodo.getChildren();
		for (INodeMenu iNodeMenu : hijos) {
			imprimeNodoStructure(deep, iNodeMenu);
		}
		
	}
	
	/**
	 * Print every child
	 * @param listChildren
	 */
	public void printChildren(Collection<INodeMenu> listChildren){
		
		for(INodeMenu child: listChildren){
			printInfoChild(child);
			Collection<INodeMenu> children = child.getChildren();
			printChildren(children);
		}
		
	}
	
	/**
	 * Print info INodeMenu
	 * @param child
	 */
	public void printInfoChild(INodeMenu child){
		
		System.out.println("Name: " + child.getText());
		System.out.println("Weight: " + child.getWeight());
		System.out.println("Url: " + child.getUrl());
		
		String iconUrl = child.getIconUrl();
		if(iconUrl != null){
			System.out.println("Icon url: " + iconUrl);
		}
		
		INodeMenu parent = child.getParent();
		if(parent == null){
			System.out.println("Parent: root");
		}else{
			System.out.println("Parent: " + child.getParent().getText());
		}
		
		Collection<String> profiles = child.getProfiles();
    	if(!profiles.isEmpty()){    		
    		String proString = "";
    		for(String profile : profiles){
    			proString += "[" + profile + "]";
    		}
    		System.out.println("Profiles: " + proString);
    	}
    	
		System.out.println("Enabled: " + child.getEnabled());
		System.out.println("Collapsed: " + child.getCollapsed());
		System.out.println("Active: " + child.getActive());
		System.out.println("Final Url: " + child.getGETUrl() + "\n");
	}
}
