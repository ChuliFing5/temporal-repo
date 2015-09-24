package proy.fing.core.dto;

import javax.persistence.Column;

public class NetworkDTO {
	
	private String name;	
	private boolean active;		
	private boolean base_ip;		
	private boolean daemon_ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isBase_ip() {
		return base_ip;
	}

	public void setBase_ip(boolean base_ip) {
		this.base_ip = base_ip;
	}

	public boolean isDaemon_ip() {
		return daemon_ip;
	}

	public void setDaemon_ip(boolean daemon_ip) {
		this.daemon_ip = daemon_ip;
	}
}
