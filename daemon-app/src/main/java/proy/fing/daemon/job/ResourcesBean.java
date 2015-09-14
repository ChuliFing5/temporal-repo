package proy.fing.daemon.job;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.californium.core.CoapObserveRelation;
import org.springframework.stereotype.Service;


@Service
public class ResourcesBean {
	
	private Map<String, CoapObserveRelation> observersMap = new HashMap<String, CoapObserveRelation>();

	public boolean areObservatingResource(String uri){
		return this.observersMap.containsKey(uri);
	}
	
	public void addObserver(String uri, CoapObserveRelation observer){
		this.observersMap.put(uri, observer);
	}
	
	

}
