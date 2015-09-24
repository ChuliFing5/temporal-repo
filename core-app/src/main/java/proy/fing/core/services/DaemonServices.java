package proy.fing.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.core.dao.DaemonDAO;
import proy.fing.core.model.Daemon;


@RestController
@RequestMapping("/daemon")
public class DaemonServices {
	
	@Autowired
	DaemonDAO daemonDAO;
	
	final static String STATE_ANOUNCED_OK = "ANOUNCEDOK";
	final static String STATE_ALREADY_ANOUNCED = "ALREADYANOUNCED";
	final static String STATE_FAIL = "FAILCALL";
	
	
	@RequestMapping(value = "/anouncedaemon", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String anounceDaemon(@RequestParam("ipDaemon") String ipDaemon, @RequestParam("ipBase") String ipBase){
		
		try {
			Daemon daemon = daemonDAO.read(ipDaemon);	
			
			if(daemon == null){
				//Se crea una nueva instacia y se guarda
				Daemon daemonNew = new Daemon();
				daemonNew.setDaemonIp(ipDaemon);
				daemonNew.setBaseIp(ipBase);
				
				daemonDAO.save(daemonNew);
				return STATE_ANOUNCED_OK;
			}
			else{
				if(daemon.getBaseIp() == ipBase)				
					return STATE_ALREADY_ANOUNCED;
				else{ 
					//VER SI SE TIENE QUE HACER MAS COSAS SI CAMBIA LA IP BASE
					
					//Se actualiza la ip base
					daemon.setBaseIp(ipBase);
					daemonDAO.update(daemon);					
					return STATE_ALREADY_ANOUNCED;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return STATE_FAIL;
		}
			
	
	}

	
}
