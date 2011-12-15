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

public abstract class AbstractMenu implements IMenu {

	private String name;
	private String description;
	private Collection<INodeMenu> children;
	private String baseUrl;
	private Collection<String> profiles;
	private Map<String, String> globalParams;
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setDescripction(String description) {
		this.description = description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Collection<INodeMenu> getChildren() {
		return children;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setChildren(Collection<INodeMenu> children) {
		this.children = children;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getBaseUrl() {
		return baseUrl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Collection<String> getProfiles() {
		return profiles;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setProfiles(Collection<String> profiles) {
		this.profiles = profiles;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<Map.Entry<String, String>> getGlobalParamskeys() {
		Iterator<Map.Entry<String, String>> it = globalParams.entrySet().iterator();
		return it;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addGlobalParam(String key, String value) {
		globalParams.put(key, value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getGlobalParam(String key) {
		return globalParams.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> getGlobalParams() {
		return globalParams;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setGlobalParams(Map<String, String> globalParams) {
		this.globalParams = globalParams;
	}
	
}
