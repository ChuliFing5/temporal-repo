package proy.fing.daemon.service.initializer;

import java.io.Console;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import proy.fing.daemon.service.container.StateContainer;

@Service
public class NetworkInitializerBean {
	
	final static String URL_SERVER = "${daemon.coreApp.url}";
	
	@PostConstruct
	public void initializeNetwork(){
		
		// 1 - le pego al servidor (mandando ip demonio y ip base) para saber si ya tiene mi demonio anunciado
		//     el servidor me responde ACCEPTED, ALREADY_ANNOUNCED
		RestTemplate restTemplate = new RestTemplate();
        String state = restTemplate.getForObject(URL_SERVER, String.class);
        System.out.println(state);        
		
		// 2 - si ya estaba anunciado, no hago nada
        
        
		// 3 - si no estaba anunciado, mando la info de mis hermanos de red, para que este los conozca (se hace solo con el job)
        if(state == "ANOUNCEDOK"){
	        StateContainer stateContainer = StateContainer.getInstance();
			stateContainer.setState(state);
        }
        
	}

}
