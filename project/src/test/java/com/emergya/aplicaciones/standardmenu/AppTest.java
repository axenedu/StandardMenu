package com.emergya.aplicaciones.standardmenu;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.emergya.aplicaciones.standardmenu.xmlmenu.XMLMenuFactory;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
   
	private static final String NAME = "Menu principal";
	private static final String ALL = "All";
	private static final String OWNER = "Owner";
	private static final String GPARAM1 = "gparam2";
	private static final String GVALUE1 = "gvalor2";
	private static final String URL = "/appBase/pagina.xhtml&gparam1=gvalor1&param1=valor&param2=valor2";
	
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
    	
    	System.out.println("Starting menuXML test\n");
    	
    	IMenuFactory factoria = new XMLMenuFactory();
    	try {
			IMenu menu1 = factoria.getMenu("menu1");
			try {
				assertTrue(checkMenuFuncionality(menu1));
			} catch (AssertionFailedError f) {
				System.out.println("An error occurred during test menuXML");
				fail();
			}
			if(checkMenuFuncionality(menu1)){				
				printMenu(menu1);
			}
		} catch (MenuException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Test menuXML finished");
    }
    
    /**
     * Check a menu. If the menu is well built, it will return true
     * @param menu
     * @return boolean
     */
    public boolean checkMenuFuncionality(IMenu menu){
    	boolean checked = true;
    	// Check leaf node
    	if(!(menu.getName().equals(NAME))){
    		return false;
    	}
    	// Check profiles
    	Collection<String> profiles = menu.getProfiles();
    	Iterator<String> proIt = profiles.iterator();
    	String pro1 = proIt.next();
    	String pro2 = proIt.next();
    	if(!(pro1.equals(ALL)) || !(pro2.equals(OWNER))){
    		return false;
    	}
    	// Check global parameters
    	String gp = menu.getGlobalParam(GPARAM1);
    	if(!gp.equals(GVALUE1)){
    		return false;
    	}
    	// Check children
    	Collection<INodeMenu> children = menu.getChildren();
    	// Check num children
    	if(children.size() != 2){
    		return false;
    	}
    	// Check a child
    	Iterator<INodeMenu> itCh = children.iterator();
    	INodeMenu ch1 = itCh.next();
    	if(!(ch1.getGETUrl().equals(URL))){
    		return false;
    	}
    	
    	return checked;
    	
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
