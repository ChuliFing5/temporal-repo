package proy.fing.daemon.model;

import java.math.BigDecimal;

public class Measure {

	private String moteIp;
	private BigDecimal value;
	private long date;
	private String ipMote;
	private String networkName;
	private int networkMote;

	public String getMoteIp() {
		return moteIp;
	}

	public void setMoteIp(String moteIp) {
		this.moteIp = moteIp;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getIpMote() {
		return ipMote;
	}

	public void setIpMote(String ipMote) {
		this.ipMote = ipMote;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public int getNetworkMote() {
		return networkMote;
	}

	public void setNetworkMote(int networkMote) {
		this.networkMote = networkMote;
	}
	
	

}
