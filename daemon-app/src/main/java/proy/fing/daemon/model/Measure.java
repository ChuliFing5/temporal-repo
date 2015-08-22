package proy.fing.daemon.model;

import java.math.BigDecimal;

public class Measure {

	private String moteIp;
	private BigDecimal value;
	private long date;

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

}