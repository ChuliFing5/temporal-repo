package proy.fing.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import proy.fing.core.dao.db.AbstractEntity;

@Entity
@Table(name = "daemons")
public class Daemon extends AbstractEntity<String> {

	@Id
	@Column(name = "daemon_ip", nullable = false)
	private String daemonIp;

	@Column(name = "base_ip", nullable = false)
	private String baseIp;
	
	
	public String getDaemonIp() {
		return daemonIp;
	}



	public void setDaemonIp(String daemonIp) {
		this.daemonIp = daemonIp;
	}



	public String getBaseIp() {
		return baseIp;
	}



	public void setBaseIp(String baseIp) {
		this.baseIp = baseIp;
	}



	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return this.daemonIp;
	}

}