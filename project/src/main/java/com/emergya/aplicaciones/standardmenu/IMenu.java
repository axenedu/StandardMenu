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
 * The Interface IMenu.
 */
public interface IMenu {
	
	
	/**
	 * Gets the id_menu.
	 *
	 * @return the id_menu
	 */
	public String getId_menu();
	
	
	/**
	 * Sets the id_menu.
	 *
	 * @param id_menu the new id_menu
	 */
	public void setId_menu(String id_menu);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name);
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * Sets the descripction.
	 *
	 * @param description the new descripction
	 */
	public void setDescripction(String description);
	
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
	 * Gets the base url.
	 *
	 * @return the base url
	 */
	public String getBaseUrl();
	
	/**
	 * Sets the base url.
	 *
	 * @param baseUrl the new base url
	 */
	public void setBaseUrl(String baseUrl);
	
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
	 * Gets the global paramskeys.
	 *
	 * @return the global paramskeys
	 */
	public Iterator<Map.Entry<String, String>> getGlobalParamskeys();
	
	/**
	 * Adds the global param.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addGlobalParam(String key, String value);
	
	/**
	 * Gets the global param.
	 *
	 * @param key the key
	 * @return the global param
	 */
	public String getGlobalParam(String key);
	
}
