package proy.fing.daemon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.daemon.dto.check.HealthCheckDTO;
import proy.fing.daemon.service.container.BaseIpContainer;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
	
	@RequestMapping("/health-check")
	public ResponseEntity<HealthCheckDTO> healthCheck(HttpServletRequest request){
		
		BaseIpContainer base = BaseIpContainer.getInstance();
		
		return new ResponseEntity<HealthCheckDTO>(new HealthCheckDTO(base.getBaseIp(), request.getRemoteAddr()),HttpStatus.OK);
	}
}
