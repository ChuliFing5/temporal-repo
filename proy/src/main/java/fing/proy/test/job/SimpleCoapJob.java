package fing.proy.test.job;

import org.eclipse.californium.core.CoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.eclipse.californium.core.CoapObserveRelation;


@Service
public class SimpleCoapJob {
	
	@Autowired
	private ResourcesBean resourcesBean;
	
	@Autowired 
	private ObserverHandle observerHandle;
	
	@Scheduled(fixedRate=30000)
	public void runJob(){
		
		String[] uris = { "coap://[aaaa::212:7408:8:808]:5683/sensores/humedad_del_aire", 
				          "coap://[aaaa::212:7404:4:404]:5683/sensores/humedad_del_aire", 
				          "coap://[aaaa::212:7405:5:505]:5683/sensores/humedad_del_aire"};
		
		for (String uri: uris){
			
			if (!this.resourcesBean.areObservatingResource(uri)){
				
				CoapClient resourceClient = new CoapClient(uri);
				
				if (resourceClient.ping()){
					CoapObserveRelation observer= resourceClient.observeAndWait(this.observerHandle);
					this.resourcesBean.addObserver(uri, observer);
				}
				else{
					System.out.print("RESOURCE:" +  uri + " not aviable\n");
				}
			}
		}
		
	}

}
