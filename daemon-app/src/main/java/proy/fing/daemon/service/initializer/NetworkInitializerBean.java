package proy.fing.daemon.service.initializer;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class NetworkInitializerBean {
	
	@PostConstruct
	public void initializeNetwork(){
		
		// 1 - le pego al servidor para saber si ya tiene mi demonio anunciado
		//     el servidor me responde ACCEPTED, ALREADY_ANNOUNCED
		
		// 2 - si ya estaba anunciado, no hago nada
		
		// 3 - si no estaba anunciado, mando la info de mis hermanos de red, para que este los conozca
	}

}
