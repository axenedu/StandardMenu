package com.emergya.aplicaciones.standardmenu.xmlmenu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParserUtil {
	
	private static final String GLOBAL_PARAMS = "global-params";
	private static final String PARAMS = "params";
	private static final String PARAM = "param";
	private static final String KEY = "key";
	private static final String PROFILES = "profiles";
	private static final String PROFILE = "profile";
	private static final String NODES = "nodes";
	private static final String NODE = "node";
	private static final String CHILDREN = "children";	
	
	/**
	 * Metodo que obtiene el valor de un nodo hoja
	 * @param doc, Document generado a partir del xml del menu
	 * @param tagName, etiqueta del nodo a tratar
	 * @param father, padre del nodo a tratar
	 * @return String, valor del nodo
	 */
	public String getValueLeafNode(Document doc, String tagName, Node father){
			
		String value = null;
		Node leaf = getChildNode(doc, father, tagName);
		if(leaf != null){			
			value = leaf.getFirstChild().getNodeValue();
		}
			
		return value;
	}
	
	/**
	 * Retornara un mapa los parametros globales del nodo father
	 * @param doc, Document generado a partir del xml
	 * @param father, nodo del que quiero obtener los parametros globales
	 * @return mapa con los parametros globlales
	 */
	public Map<String, String> getGloblalParameters(Document doc, Node father){
		
		Map<String, String> globalParameters = new HashMap<String, String>();
		
		Node gpNode = getChildNode(doc, father, GLOBAL_PARAMS);
		
		if(gpNode != null){			
			Element gpElement = (Element)gpNode;
			NodeList params = gpElement.getElementsByTagName(PARAM);
			for(int j=0;j<params.getLength();j++){
				Node param = params.item(j);
				globalParameters.put(getValueAttribute(param, KEY),param.getFirstChild().getNodeValue());
			}
		}
		
		return globalParameters;
	}
	
	/**
	 * Retorna un mapa con los parametros del nodo father
	 * @param doc, Document generado a partir del xml
	 * @param father, nodo del que queremos obtener los parametros
	 * @return Map
	 */
	public Map<String, String> getParameters(Document doc, Node father){
		
		Map<String, String> parameters = new HashMap<String, String>();
		
		Node pNode = getChildNode(doc, father, PARAMS);
		
		if(pNode != null){			
			Element pElement = (Element)pNode;
			NodeList params = pElement.getElementsByTagName(PARAM);
			for(int j=0;j<params.getLength();j++){
				Node param = params.item(j);
				parameters.put(getValueAttribute(param, KEY),param.getFirstChild().getNodeValue());
			}
		}
		
		return parameters;
	}
	
	/**
	 * Retorna el valor del atributo especificiadodel nodo n
	 * @param n, nodo del que queremos obtener el valor del atributo
	 * @param nameAttribute, atributo
	 * @return String 
	 */
	public String getValueAttribute(Node n, String nameAttribute){
		
		Element e = (Element)n;
		String value = e.getAttribute(nameAttribute);
		
		return value;
	}
	
	/**
	 * De todos los nodos con dicho tagName, me devuelve el hijo correspondiente
	 * al padre pasado como parametro
	 * @param doc, Document generado a partir del xml
	 * @param father, nodo padre
	 * @param tagName, etiqueta del nodo a obtener
	 * @return
	 */
	public Node getChildNode(Document doc, Node father, String tagName){
		
		Node childNode = null;
		NodeList nodeList = doc.getElementsByTagName(tagName);
		if(nodeList.getLength() > 0){
			for(int i=0;i<nodeList.getLength();i++){
				Node node = nodeList.item(i);
				if(node.getParentNode().equals(father)){
					childNode = node;
				}
			}
		}
		
		return childNode;
	}
	
	/**
	 * Lista de perfiles de un nodo
	 * @param doc, Document generado a partir del xml
	 * @param father, nodo padre a tratar
	 * @return Lista de perfiles
	 */
	public List<String> getProfiles(Document doc, Node father){
		
		List<String> profilesList = new LinkedList<String>();
		
		Node gpNode = getChildNode(doc, father, PROFILES);
		
		if(gpNode != null){			
			Element gpElement = (Element)gpNode;
			NodeList profiles = gpElement.getElementsByTagName(PROFILE);
			for(int j=0;j<profiles.getLength();j++){
				Node profile = profiles.item(j);
				profilesList.add(profile.getFirstChild().getNodeValue());
			}
		}
		
		return profilesList;
	}
	
	/**
	 * Obtiene el boolean de la etiqueta correspondiente
	 * @param doc, Document generado a partir del xml
	 * @param tagname, etiqueta de la que quiero obtener el boolean
	 * @param father, nodo padre
	 * @return boolean
	 */
	public boolean getBoolean(Document doc, String tagname, Node father){
		boolean bool = false;
		if(getValueLeafNode(doc, tagname, father)!= null && getValueLeafNode(doc, tagname, father).equals("true")){
			bool = true;
		}
		
		return bool;
	}
	
	/**
	 * Devuelve true si el elemento raiz contiene la etiqueta <nodes>
	 * @param doc, Document generado a partir del xml
	 * @return boolean
	 */
	public boolean rootHasNodes(Document doc){
		
		boolean hasNodes = false;
		Node root = doc.getDocumentElement();
		Node nodes = getChildNode(doc, root, NODES);
		if(nodes != null){
			hasNodes = true;
		}
				
		return hasNodes;
	}
	/**
	 * Retorna la lista de <node> que cuelga de la raiz
	 * @param doc, Document generado a partir del xml
	 * @return NodeList
	 */
	public List<Node> getListRootNodes(Document doc){
		
		NodeList nodeList = null;
		Node root = doc.getDocumentElement();
		// Nodo <nodes>
		Node nodes = getChildNode(doc, root, NODES);
		Element element = (Element)nodes;
		// Lista de <node> de <nodes>
		nodeList = element.getElementsByTagName(NODE);
		List<Node> list = new LinkedList<Node>();
		for(int i=0;i<nodeList.getLength();i++){
			Node n = nodeList.item(i);
			if(n.getParentNode().equals(nodes)){
				list.add(n);
			}
		}
		
		return list;
	}
	
	/**
	 * Dado un nodo padre se retornara una lista con sus nodos hijos
	 * @param doc, Document generado a partir del xml
	 * @param father, Nodo del que vamos a obtener la lista de hijos
	 * @return NodeList
	 */
	public List<Node> getListNodes(Document doc, Node father){
		
		NodeList nodeList = null;
		Node nodes = getChildNode(doc, father, CHILDREN);
		Element element = (Element)nodes;
		nodeList = element.getElementsByTagName(NODE);
		
		List<Node> list = new LinkedList<Node>();
		for(int i=0;i<nodeList.getLength();i++){
			Node n = nodeList.item(i);
			if(n.getParentNode().equals(nodes)){
				list.add(n);
			}
		}
		
		return list;
		
	}
	
	/**
	 * Devuelve true si node tiene hijos
	 * @param doc, Document generado a partir del xml
	 * @param node, node que estamos tratando
	 * @return boolean
	 */
	public boolean nodeHasChildren(Document doc, Node node){
		
		boolean hasNodes = false;
		Node nodes = getChildNode(doc, node, CHILDREN);
		if(nodes != null){
			hasNodes = true;
		}
				
		return hasNodes;
	}
}
