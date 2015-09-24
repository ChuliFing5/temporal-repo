package proy.fing.core.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class MoteDTO {
	
	private String id;	
	private String network_name;	
	private String ip;	
	private boolean active;	
	
	
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
