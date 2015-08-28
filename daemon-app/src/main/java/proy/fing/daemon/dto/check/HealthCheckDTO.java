package proy.fing.daemon.dto.check;

public class HealthCheckDTO {

	private String baseIp;
	private String sourceIp;

	public HealthCheckDTO(String baseIp, String sourceIp) {
		this.baseIp = baseIp;
		this.sourceIp = sourceIp;

	}

	public String getBaseIp() {
		return baseIp;
	}

	public void setBaseIp(String baseIp) {
		this.baseIp = baseIp;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

}
