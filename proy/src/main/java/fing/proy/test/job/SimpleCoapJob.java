package fing.proy.test.job;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.WebLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.eclipse.californium.core.CoapObserveRelation;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@Service
public class SimpleCoapJob {
	
	//Variables de prueba (con coap server implementado local)
	final static String IPBASE = "localhost";
	final static String RED = "red1";
	
	
	@Autowired
	private ResourcesBean resourcesBean;
	
	@Autowired 
	private ObserverHandle observerHandle;
	
	@Scheduled(fixedRate=30000)
	public void runJob(){
		
		//Cliente coap de la base de la red
		final CoapClient cliente = new CoapClient("coap://" + IPBASE + "/network/status");
    	
   	 	if(cliente.ping())
        {
   	 		//Se obtienen las ip de los motes de la red (en el recurso /network/status)
   	 		CoapResponse ipMotesRed = cliente.get();
   	 		if(ipMotesRed.isSuccess())
   	 		{
   	 			String ipMotesRedText = ipMotesRed.getResponseText();
	 			   	 			
	 			JsonParser JsonParser = new JsonParser(); 
	 			JsonElement element = JsonParser.parse(ipMotesRedText);
	 			JsonArray arrayIp = element.getAsJsonArray();
	 		   	
	 			//Se itera sobre las ip obtenidas
	 			Iterator<JsonElement> iterator = arrayIp.iterator();
	 			
	 			while (iterator.hasNext()) {
					 JsonElement elementoActual = iterator.next();
					 final String ipActual = elementoActual.getAsString();
					 
					//Se obtienen todos los recursos publicados por los mote
		   	 		CoapClient clienteMote = new CoapClient("coap://" + ipActual + "/");
		   	 		
		   	 		if(clienteMote.ping()){
		   	 		
				   		 Set<WebLink> discovery = clienteMote.discover();
				   		 
				   		 //Se itera sobre los recursos del mote actual
				   		 for(WebLink recurso : discovery)
					     {				   			 
					            final String URIRec = recurso.getURI();
					            
					            //Si el recurso no esta siendo observado, se hace le observeandwwait y se agrega al ResourcesBean
					            if(!this.resourcesBean.areObservatingResource(URIRec)){
					            					            
						            //FALTA: ver como determinar si hay que observar o no (hay recursos que no se observan)
						            if(URIRec.contains("temperature") || URIRec.contains("humedad")){
						            	
						            	//Cliente coap del recurso
							            CoapClient clienteRecurso = new CoapClient("coap://" + ipActual + URIRec);
							            					            
							            
							            CoapObserveRelation obs = clienteRecurso.observeAndWait(new CoapHandler()
							            {
							                
							                public void onLoad(CoapResponse response)
							                {
							                    if(response != null){
								                	
								                    String msg = response.getResponseText();
								                    
								                    System.out.println("OnLoad: "+msg);
								                    
								                    //Cuando se recibe medición se debe mandar a la cola de mensajes						                    
								                   					
								                    //System.out.println("OnLoad: Medida Mandada - { IdMote: " + nuevaMedida.IdMote + ", IdRed: "+nuevaMedida.IdRed+", SensorURI: "+nuevaMedida.SensorURI+", Valor: "+nuevaMedida.Valor +"}");
								                    
								                    if(msg.contains("TooManyObservers"))
								                    {
								                        return;
								                    }
							                    }
							                    else{
							                    	System.out.println("OnLoad: Response es null");
							                    }
							                    	
							                }
					
							                
							                public void onError()
							                {
							                    //se "perdio la conexion" con el mote, el recurso pasa a estado desuscripto
							                    String error = "Error de conexion";
							                    
							                    System.out.println("OnError: "+ error);
							                }
							            }); 
							            
							            this.resourcesBean.addObserver(URIRec, obs);
						            }
						         }
					     }
		   	 		}
				}
	 			
   	 		}
        }
		
//		String[] uris = { "coap://[aaaa::212:7408:8:808]:5683/sensores/humedad_del_aire", 
//				          "coap://[aaaa::212:7404:4:404]:5683/sensores/humedad_del_aire", 
//				          "coap://[aaaa::212:7405:5:505]:5683/sensores/humedad_del_aire"};
//		
//		for (String uri: uris){
//			
//			if (!this.resourcesBean.areObservatingResource(uri)){
//				
//				CoapClient resourceClient = new CoapClient(uri);
//				
//				if (resourceClient.ping()){
//					CoapObserveRelation observer= resourceClient.observeAndWait(this.observerHandle);
//					this.resourcesBean.addObserver(uri, observer);
//				}
//				else{
//					System.out.print("RESOURCE:" +  uri + " not aviable\n");
//				}
//			}
//		}
		
	}

}
