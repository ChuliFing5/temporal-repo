package proy.fing.daemon.service.initializer;

import java.io.Console;
import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import proy.fing.daemon.service.container.BaseIpContainer;
import proy.fing.daemon.service.container.StateContainer;

@Service
public class NetworkInitializerBean {
	
	final static String URL_SERVER = "http://localhost:8081/core-app/daemon";
	final static String SERVICEANOUNCE = "/anouncedaemon?ipDaemon=%s&ipBase=%s";
	
	@PostConstruct
	public void initializeNetwork(){
		try{
			// 1 - le pego al servidor (mandando ip demonio y ip base) para saber si ya tiene mi demonio anunciado
			//     el servidor me responde ACCEPTED, ALREADY_ANNOUNCED
			RestTemplate restTemplate = new RestTemplate();
			
			String ipDaemon = InetAddress.getLocalHost().getHostAddress();
			BaseIpContainer baseIpContainer =  BaseIpContainer.getInstance();
			String ipBase= baseIpContainer.getBaseIp();
			
			String uriService = URL_SERVER + String.format(SERVICEANOUNCE,ipDaemon,ipBase );		
	        String state = restTemplate.getForObject(uriService, String.class);
	        System.out.println(state);        
			
			// 2 - si ya estaba anunciado, no hago nada        
	        
			// 3 - si no estaba anunciado, mando la info de mis hermanos de red, para que este los conozca (o se hace solo con el job?)
	        if(state.equals("ANOUNCEDOK") || state.equals("ALREADYANOUNCED") ){
		        StateContainer stateContainer = StateContainer.getInstance();
				stateContainer.setState("ANOUNCEDOK");
	        }
		}
        catch(Exception e){
        	e.printStackTrace(); 
        }
	}

}
