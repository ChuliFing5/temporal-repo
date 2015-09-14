package proy.fing.daemon.dto.measures;

import java.io.Serializable;
import java.math.BigDecimal;

public class MeasureDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal value;
	private String networkName;
	private int networkMote;
	private String ipMote;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
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

	public String getIpMote() {
		return ipMote;
	}

	public void setIpMote(String ipMote) {
		this.ipMote = ipMote;
	}

	
}
