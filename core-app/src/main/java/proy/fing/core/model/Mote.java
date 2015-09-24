package proy.fing.core.model;

import javax.persistence.Column;
import javax.persistence.Id;

import proy.fing.core.dao.db.AbstractEntity;

public class Mote extends AbstractEntity<String>{

	@Id	
	@Column(name = "id", nullable = false)
	private String id;
	
	@Id	
	@Column(name = "network_name", nullable = false)
	private String network_name;
	
	@Column(name = "ip", nullable = false)
	private String ip;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	
	@Override
	public String getIdentifier() {
		
		return this.id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNetwork_name() {
		return network_name;
	}


	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
