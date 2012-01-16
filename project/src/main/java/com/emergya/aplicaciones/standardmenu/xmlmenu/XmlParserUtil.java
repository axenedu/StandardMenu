package com.emergya.aplicaciones.standardmenu.xmlmenu;

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
	private static final String GLOBAL_PARAMS_NODE = "global-paramsNode";
	private static final String PARAMS = "params";
	private static final String PARAM = "param";
	private static final String KEY = "key";
	private static final String PROFILES = "profiles";
	private static final String PROFILES_NODE = "profilesNode";
	private static final String PROFILE = "profile";
	private static final String NODES = "nodes";
	private static final String NODE = "node";
	private static final String CHILDREN = "children";	
	
	/**
	 * Gets info leaf node
	 * @param doc, Document generated from menu xml
	 * @param tagName, node tag 
	 * @param father, parent node
	 * @return String, value node
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
	 * gets a map with global params
	 * @param doc, Document generated from menu xml
	 * @param father, node with the info
	 * @return map with global params
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
	 * gets a map with global params of a Node tag
	 * @param doc, Document generated from menu xml
	 * @param father, node with the info
	 * @return map with global params
	 */
	public Map<String, String> getGloblalParametersNode(Document doc, Node father){
		
		Map<String, String> globalParameters = new HashMap<String, String>();
		
		Node gpNode = getChildNode(doc, father, GLOBAL_PARAMS_NODE);
		
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
	 * gets a map with params
	 * @param doc, Document generated from menu xml
	 * @param father, node with the info
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
	 * gets the value of attribute specified with nameAttribute
	 * @param n, treated node
	 * @param nameAttribute, attribute name
	 * @return String Value
	 */
	public String getValueAttribute(Node n, String nameAttribute){
		
		Element e = (Element)n;
		String value = e.getAttribute(nameAttribute);
		
		return value;
	}
	
	/**
	 * 
	 * Of all nodes with the specified lable, it gets one whose parent matches with father
	 * @param doc, Document generated from menu xml
	 * @param father, parent node
	 * @param tagName, node tagname
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
	 * Gets profile list of a menu
	 * @param doc, Document generated from menu xml
	 * @param father, parent node
	 * @return profile list
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
	 * Gets profile list of a node tag
	 * @param doc, Document generated from menu xml
	 * @param father, parent node
	 * @return profile list
	 */
	public List<String> getProfilesNode(Document doc, Node father){
		
		List<String> profilesList = new LinkedList<String>();
		
		Node gpNode = getChildNode(doc, father, PROFILES_NODE);
		
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
	 * Gets boolean value of specified tagname
	 * @param doc, Document generated from menu xml
	 * @param tagname, specified tagname
	 * @param father, parent node
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
	 * Return true if xml root has children
	 * @param doc, Document generated from menu xml
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
	 * Return root children
	 * @param doc, Document generated from menu xml
	 * @return node list
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
	 * Return Node list of father
	 * @param doc, Document generated from menu xml
	 * @param father, specified node
	 * @return node list
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
	 * Return true if node has children
	 * @param doc, Document generated from menu xml
	 * @param node, specified node
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
