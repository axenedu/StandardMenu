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

public abstract class AbstractNodeMenu implements INodeMenu, Comparable<INodeMenu> {

	private IMenu menu;
	
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
	 * 
	 * {@inheritDoc}
	 */
	public IMenu getMenu() {
		return menu;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void setMenu(IMenu menu) {
		this.menu = menu;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Collection<INodeMenu> getChildren() {
		return children;
	}

	public void setChildren(Collection<INodeMenu> children) {
		this.children = children;		
	}

	public INodeMenu getParent() {
		return parent;
	}

	public void setParent(INodeMenu parent){
		this.parent = parent;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void addParam(String key, String value) {
		params.put(key, value);
	}

	public String getParamValue(String key) {
		return params.get(key);
	}

	public void addGlobalParam(String key, String value) {
		globalParams.put(key, value);		
	}

	public void removeGlobalParam(String key) {
		globalParams.remove(key);
	}

	public Iterator<String> getGlobalParams() {
		Iterator it = globalParams.entrySet().iterator();
		return it;
	}
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void setGlobalParams(Map<String, String> globalParams) {
		this.globalParams = globalParams;
	}
	
	public Collection<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(Collection<String> profiles) {
		this.profiles = profiles;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String GETUrl() {
		String url = this.getUrl();
		
		Iterator it_global = this.getGlobalParams();
		while (it_global.hasNext()) {
	        Map.Entry pairs_glo = (Map.Entry)it_global.next();
	        url += "&" + pairs_glo.getKey() + "=" + pairs_glo.getValue();
	    }
		
		Iterator it_par = params.entrySet().iterator();
		while (it_par.hasNext()) {
	        Map.Entry pairs_par = (Map.Entry)it_par.next();
	        url += "&" + pairs_par.getKey() + "=" + pairs_par.getValue();
	    }
		
		return url;
	}

	
	public int compareTo(INodeMenu nm) {
		int cmp = 0;
		
		if(this.weight != nm.getWeight()){
			cmp = this.compareTo(nm);
		}else{
			cmp = this.getText().compareTo(nm.getText());
		}
		
		return cmp;
	}

}
