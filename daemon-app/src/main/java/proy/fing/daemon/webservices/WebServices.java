package proy.fing.daemon.webservices;

import javax.xml.ws.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.daemon.service.container.StateContainer;

@RestController
@RequestMapping("/services")
public class WebServices {

	@RequestMapping(value = "/setstate", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String anounceDaemon(@RequestBody String state)
	{
		try{
			StateContainer stateContainer = StateContainer.getInstance();
			stateContainer.setState(state);
			
			return "OK";
		}
		catch(Exception e){
			
			return e.getMessage();
		}
	}
}
