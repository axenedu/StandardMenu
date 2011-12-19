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

public abstract class AbstractNodeMenu implements INodeMenu {

	private String text;
	private int weight;
	private String url;
	private String iconUrl;
	private Collection<INodeMenu> children;
	private INodeMenu parent;
	private Map<String, String> params;
	private Map<String, String> globalParams;
	private Collection<String> profiles;
	private boolean enabled;
	private boolean collapsed;
	private boolean active;
	
	/**
	 * {@inheritDoc}
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Collection<INodeMenu> getChildren() {
		return children;
	}

	public void setChildren(Collection<INodeMenu> children) {
		this.children = children;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public INodeMenu getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParent(INodeMenu parent){
		this.parent = parent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> getParams() {
		return params;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addParam(String key, String value) {
		params.put(key, value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getParamValue(String key) {
		return params.get(key);
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
	public void removeGlobalParam(String key) {
		globalParams.remove(key);
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<Map.Entry<String, String>> getGlobalParams() {
		Iterator<Map.Entry<String, String>> it = globalParams.entrySet().iterator();
		return it;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setGlobalParams(Map<String, String> globalParams) {
		this.globalParams = globalParams;
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
	public boolean getEnabled() {
		return enabled;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean getCollapsed() {
		return collapsed;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getGETUrl() {
		String url = this.getUrl();
		
		Iterator<Map.Entry<String, String>> it_global = this.getGlobalParams();
		while (it_global.hasNext()) {
	        Map.Entry<String,String> pairs_glo = (Map.Entry<String,String>)it_global.next();
	        url += "&" + pairs_glo.getKey() + "=" + pairs_glo.getValue();
	    }
		
		Iterator<Map.Entry<String, String>> it_par = params.entrySet().iterator();
		while (it_par.hasNext()) {
	        Map.Entry<String, String> pairs_par = (Map.Entry<String, String>)it_par.next();
	        url += "&" + pairs_par.getKey() + "=" + pairs_par.getValue();
	    }
		
		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(INodeMenu nm) {
		int cmp = 0;
		
		if(nm != null){			
			if(this.weight != nm.getWeight()){
				cmp = new Integer(this.weight).compareTo(new Integer(nm.getWeight()));
			}else{
				cmp = this.getText().compareToIgnoreCase(nm.getText());
			}
		}
		
		return cmp;
	}

}
