package proy.fing.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.daemon.dao.MeasureDAO;
import proy.fing.daemon.dto.measures.MeasureDTO;
import proy.fing.daemon.model.Measure;
import proy.fing.daemon.service.sender.MessageSender;

@RestController
@RequestMapping("/measure")
public class MeasureController {
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private MeasureDAO measureDAO;
	
	@RequestMapping(value = "/send", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void reciveMesure(@RequestBody MeasureDTO measureDTO){
		this.messageSender.send(measureDTO);
		
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void saveMesure(@RequestBody Measure measure){
		this.measureDAO.save(measure);
		
	}

}
