package proy.fing.daemon.service.container;

public class StateContainer {
	
	private static StateContainer instance = null;
	
	private String state;
	
	public static StateContainer getInstance(){
		if (instance == null){
			instance = new StateContainer();
		}
		return instance;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
