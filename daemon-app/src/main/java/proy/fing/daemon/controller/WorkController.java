package proy.fing.daemon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.daemon.dto.measures.MeasureDTO;

@RestController
@RequestMapping("/work")
public class WorkController {
	
	@RequestMapping(value = "/start", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void reciveMesure(@RequestBody MeasureDTO measureDTO){
		
	}

}
