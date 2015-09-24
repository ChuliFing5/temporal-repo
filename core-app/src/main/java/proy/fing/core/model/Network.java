package proy.fing.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import proy.fing.core.dao.db.AbstractEntity;


@Entity
@Table(name = "networks")
public class Network extends AbstractEntity<String> {

	@Id
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@Column(name = "base_ip", nullable = false)
	private boolean base_ip;
	
	@Column(name = "daemon_ip", nullable = false)
	private boolean daemon_ip;

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return this.name;
	}

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
