package proy.fing.core.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "measure")
public class Measure {

	@Id
	private String id;

	private BigDecimal value;
	private int networkMote;
	private String networkName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getNetworkMote() {
		return networkMote;
	}

	public void setNetworkMote(int networkMote) {
		this.networkMote = networkMote;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	
	public void generateId(){
		this.id = this.networkName + "-" + String.valueOf(this.networkMote);
	}

}
