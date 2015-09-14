package proy.fing.daemon.job;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.WebLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import proy.fing.daemon.dao.MeasureDAO;
import proy.fing.daemon.dto.measures.MeasureDTO;
import proy.fing.daemon.job.ResourcesBean;
import proy.fing.daemon.model.Measure;
import proy.fing.daemon.service.sender.MessageSender;

@Service
public class NetworkDiscoverJob {
	
	//Variables de prueba (con coap server implementado local)
	final static String BASEIP = "localhost";
	final static String NETWORKNAME = "red1";

	@Autowired
	private ResourcesBean resourcesBean;
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private MeasureDAO measureDAO;
	
	
	@Scheduled(cron="${daemon.config.job.cron.networkDiscover}")
	public void discoverNetwork(){
		
		//Cliente coap de la base de la red
		final CoapClient cliente = new CoapClient("coap://" + BASEIP + "/network/status");
    	
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
	 		   	
	 			//Se itera sobre las ip obtenidas del resto de los motes
	 			Iterator<JsonElement> iterator = arrayIp.iterator();
	 			
	 			while (iterator.hasNext()) {
					 JsonElement elementoActual = iterator.next();
					 final String ipMoteActual = elementoActual.getAsString();
					 
					//Se obtienen todos los recursos publicados por los mote
		   	 		CoapClient clienteMote = new CoapClient("coap://" + ipMoteActual + "/");
		   	 		
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
							            CoapClient clienteRecurso = new CoapClient("coap://" + ipMoteActual + URIRec);
							            					            
							            //Implementación del handler
							            CoapObserveRelation obs = clienteRecurso.observeAndWait(new CoapHandler()
								            {
								                
								                public void onLoad(CoapResponse response)
								                {
								                    if(response != null){
									                	
									                    String msg = response.getResponseText();
									                    
									                    if(msg.contains("TooManyObservers"))
									                    {
									                        return;
									                    }
									                    
									                    BigDecimal measureValue = new BigDecimal(msg);
									                    System.out.println("OnLoad: "+msg);
									                    
									                    //Cuando se recibe medición se debe mandar a la cola de mensajes	
									                    Measure newMeasure = new Measure();
									                    newMeasure.setIpMote(ipMoteActual);
									                    newMeasure.setNetworkName(NETWORKNAME);
									                    newMeasure.setValue(measureValue);
									                    
									                    try{
								                   			
									                    	messageSender.send(newMeasure);
									                    }
									                    catch(Exception e){
									                    	//Si no se puede mandar a la cola, se almacena en la base de datos local
									                    	measureDAO.save(newMeasure);
									                    }
									                    //System.out.println("OnLoad: Medida Mandada - { IdMote: " + nuevaMedida.IdMote + ", IdRed: "+nuevaMedida.IdRed+", SensorURI: "+nuevaMedida.SensorURI+", Valor: "+nuevaMedida.Valor +"}");
									                    
									                    
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
								            }
							            ); 
							            
							            this.resourcesBean.addObserver(URIRec, obs);
						            }
						         }
					     }
		   	 		}
				}
	 			
   	 		}
        }
		
	}

}
