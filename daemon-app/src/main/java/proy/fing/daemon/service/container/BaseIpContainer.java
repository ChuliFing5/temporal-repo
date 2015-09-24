package proy.fing.daemon.service.container;

public class BaseIpContainer {
	
	private static BaseIpContainer instance = null;
	
	private String networkBaseIp;
	
	public static BaseIpContainer getInstance(){
		if (instance == null){
			instance = new BaseIpContainer();
		}
		return instance;
	}
	
	public void setBaseIp(String baseIp){
		this.networkBaseIp = baseIp;
	}
	
	public String getBaseIp(){
		return this.networkBaseIp;
	}

}
