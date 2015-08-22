package proy.fing.daemon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.daemon.dto.measures.MeasureDTO;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
	
	@RequestMapping("/health-check")
	public ResponseEntity<MeasureDTO> healthCheck(){
		return new ResponseEntity<MeasureDTO>(new MeasureDTO(),HttpStatus.OK);
	}
}
