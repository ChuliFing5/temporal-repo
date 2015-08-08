package proy.fing.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.web.dto.measures.MeasureDTO;
import proy.fing.web.sender.MessageSender;

@RestController
@RequestMapping("/measure")
public class MeasureController {
	
	@Autowired
	private MessageSender messageSender;
	
	@RequestMapping(value = "/send", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void reciveMesure(@RequestBody MeasureDTO measureDTO){
		this.messageSender.send(measureDTO);
		
	}

}
