package com.emergya.aplicaciones.standardmenu.xmlmenu;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.emergya.aplicaciones.standardmenu.IMenu;
import com.emergya.aplicaciones.standardmenu.INodeMenu;
import com.emergya.aplicaciones.standardmenu.MenuException;

public class XmlParser {
	
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String BASE_URL = "base-url";
	private static final String NODES = "nodes";
	private static final String TEXT = "text";
	private static final String WEIGHT = "weight";
	private static final String URL = "url";
	private static final String ICONURL = "iconUrl";
	private static final String ENABLED = "enabled";
	private static final String COLLAPSED = "collapsed";
	private static final String ACTIVE = "active";
	private static final String MSG = "There was an error in createMenu";
	
	/**
	 * Dado una ruta que apunta a un xml se generará un menu
	 * @param path, ruta del xml que contiene la estructura del menu
	 * @return IMenu, menu conformado
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws MenuException 
	 */
	public IMenu createMenu(String path) throws MenuException{
		
		IMenu menu = null;
		Document doc = null;
		File f;
		
		try {
			f = new File(path);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(f);
			menu = readMenu(doc);
		} catch (Exception e) {
			throw new MenuException(e, MSG, new Integer(0), "");
		}
		
		return menu;
		
	}

	/**
	 * Dado un document generará una instancia de IMenu
	 * @param doc, Document obtenido a partir del xml
	 * @return IMenu
	 */
	public IMenu readMenu(Document doc){
		
		// Establecemos los valores del menu principal
		IMenu menu = new XMLMenu();
		XmlParserUtil xpu = new XmlParserUtil();
		Node root = doc.getDocumentElement();
		
		menu.setName(xpu.getValueLeafNode(doc, NAME, root));
		menu.setDescripction(xpu.getValueLeafNode(doc, DESCRIPTION, root));
		menu.setBaseUrl(xpu.getValueLeafNode(doc, BASE_URL, root));
		menu.setProfiles(xpu.getProfiles(doc, root));
		
		Map<String, String> globalParams = xpu.getGloblalParameters(doc,root);
		Iterator<Map.Entry<String, String>> it = globalParams.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, String> mapEntry = (Map.Entry<String, String>)it.next();
			menu.addGlobalParam(mapEntry.getKey(), mapEntry.getValue());
		}
		
		// Recursividad que recorrera los hijos
		if(xpu.rootHasNodes(doc)){
			//Devuelve una lista de nodos de nivel 0
			List<Node> nodeList = xpu.getListRootNodes(doc);
			
			// Lista de hijos del menu
			List<INodeMenu> childrenList = new LinkedList<INodeMenu>();
			int nivel = 0;
			
			for(int i=0; i<nodeList.size();i++){
				Node n = nodeList.get(i);
				//Construimos la información del nodo y la pasamos al objeto leyendo del xml
				INodeMenu nm = parseNode(doc, n, null);
				
				//Tomamos los hijos de forma recursiva
				List<INodeMenu> children = getChildren(doc,n,nm); 
				nm.setChildren(children);
				
				//añadimos el nodo de nivel 0 al menu
				childrenList.add(nm);
			}
			menu.setChildren(childrenList);
		}
		
		return menu;
	}
	
	/**
	 * Método recursivo que recorrera todos los nodos del menu
	 * @param n, Nodo que estamos tratando
	 * @param doc, doc obtenido a partir dl xml
	 * @return INodeMenu, cada hijo del nodo n
	 */	
	public List<INodeMenu> getChildren(Document doc, Node node, INodeMenu parent){
		
		//Lista de Hijos a devolver
		List<INodeMenu> children = new LinkedList<INodeMenu>();
		
		XmlParserUtil xpu = new XmlParserUtil();

		if(xpu.nodeHasChildren(doc, node)){
			
			//Lista de nodos del nivel inferior al padre -> variable parent
			List<Node> list = xpu.getListNodes(doc,node);
			
			INodeMenu child = null;
			
			for(int i=0; i<list.size();i++){
				//Parsear el hijo y convertirlo a nodo
				Node childNode = list.get(i);
				child = parseNode(doc,childNode,parent);
				children.add(child);
				
				List<INodeMenu> hijos = getChildren(doc, childNode, child);
				
				child.setChildren(hijos);
			}

		}
		
		return children;
	}
	/**
	 * Dado un nodo, setea todas sus propiedades
	 * @param doc, Document generado a partir del xml
	 * @param n, Node que contiene la info del nodo
	 * @param father, INodeMenu padre
	 * @return INodeMenu seteado
	 */
	public INodeMenu parseNode(Document doc, Node n, INodeMenu father){
		
		INodeMenu nodeMenu = new XMLMenuNode();
		// Parseo directo desde el xml
		XmlParserUtil parser = new XmlParserUtil();
		
		String text = parser.getValueLeafNode(doc, TEXT, n);
		int weight = Integer.parseInt(parser.getValueLeafNode(doc, WEIGHT, n));
		String url = parser.getValueLeafNode(doc, URL, n);
		String iconUrl = parser.getValueLeafNode(doc, ICONURL, n);
		List<INodeMenu> children = null;
		Map<String, String> globalParams = parser.getGloblalParameters(doc, n);
		Map<String, String> params = parser.getParameters(doc, n);
		List<String> profiles = parser.getProfiles(doc, n);
		boolean enabled = parser.getBoolean(doc, ENABLED, n); 		
		boolean collapsed = parser.getBoolean(doc, COLLAPSED, n);
		boolean active = parser.getBoolean(doc, ACTIVE, n);
		
		// Insercion valores en el nodo
		nodeMenu.setText(text);
		nodeMenu.setWeight(weight);
		nodeMenu.setUrl(url);
		nodeMenu.setIconUrl(iconUrl);
		nodeMenu.setChildren(children);
		nodeMenu.setParent(father);
		
		Iterator<Map.Entry<String, String>> it = globalParams.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> mapEntry = (Map.Entry<String, String>)it.next();
			nodeMenu.addGlobalParam(mapEntry.getKey(), mapEntry.getValue());
		}
		
		Iterator<Map.Entry<String, String>> it2 = params.entrySet().iterator();
		while(it2.hasNext()){
			Map.Entry<String, String> mapEntry2 = (Map.Entry<String, String>)it2.next();
			nodeMenu.addParam(mapEntry2.getKey(), mapEntry2.getValue());
		}
		
		nodeMenu.setProfiles(profiles);
		nodeMenu.setEnabled(enabled);
		nodeMenu.setCollapsed(collapsed);
		nodeMenu.setActive(active);
		
		return nodeMenu;
	}
	
}
