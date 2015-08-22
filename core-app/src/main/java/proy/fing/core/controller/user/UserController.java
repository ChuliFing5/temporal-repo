package proy.fing.core.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.core.dto.UserDTO;
import proy.fing.core.service.interfaces.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> createNewUser(@RequestBody UserDTO userDTO){
		
		this.userService.createUser(userDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	


}
