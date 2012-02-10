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

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * The Interface INodeMenu.
 */
public interface INodeMenu extends Comparable<INodeMenu>{

	/**
	 * Gets the id_node.
	 *
	 * @return the id_node
	 */
	public String getId_node();
	
	/**
	 * Sets the id_node.
	 *
	 * @param id_node the id_node
	 */
	public void setId_node(String id_node);
	
	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText();
	
	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text);
	
	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight();
	
	/**
	 * Sets the weight.
	 *
	 * @param text the new weight
	 */
	public void setWeight(int weight);
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl();
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url);
	
	/**
	 * Gets the icon url.
	 *
	 * @return the icon url
	 */
	public String getIconUrl();
	
	/**
	 * Sets the icon url.
	 *
	 * @param iconUrl the new icon url
	 */
	public void setIconUrl(String iconUrl);
	
	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public Collection<INodeMenu> getChildren();
	
	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(Collection<INodeMenu> children);
	
	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public INodeMenu getParent();
	
	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(INodeMenu parent);
	
	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public Map<String, String> getParams();
	
	/**
	 * Adds the param.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addParam(String key, String value);
	
	/**
	 * Gets the param value.
	 *
	 * @param key the key
	 * @return the param value
	 */
	public String getParamValue(String key);
	
	/**
	 * Adds the global param.
	 *
	 * @param key the key
	 */
	public void addGlobalParam(String key, String value);
	
	/**
	 * Removes the global param.
	 *
	 * @param key the key
	 */
	public void removeGlobalParam(String key);
	
	/**
	 * Gets the global params.
	 *
	 * @return the global params
	 */
	public Iterator<Map.Entry<String, String>> getGlobalParams();
	
	/**
	 * Gets the profiles.
	 *
	 * @return the profiles
	 */
	public Collection<String> getProfiles();
	
	/**
	 * Sets the profiles.
	 *
	 * @param profiles the new profiles
	 */
	public void setProfiles(Collection<String> profiles);
	
	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public boolean getEnabled();
	
	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Gets the collapsed.
	 *
	 * @return the collapsed
	 */
	public boolean getCollapsed();
	
	/**
	 * Sets the collapsed.
	 *
	 * @param collapsed the new collapsed
	 */
	public void setCollapsed(boolean collapsed);
	
	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public boolean getActive();
	
	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active);
	
	/**
	 * Gets the url. Parameters will be included.
	 *
	 * @return the string
	 */
	public String getGETUrl();
	
	/**
	 * Compares two INodeMenu to determine the position
	 * @param INodeMenu
	 * @return int
	 */
	public int compareTo(INodeMenu nm);
	
}

