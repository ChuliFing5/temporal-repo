package proy.fing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import proy.fing.web.model.User;
import proy.fing.web.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@ResponseBody
	@RequestMapping(value = "/{userName}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable(value="userName") String userName){
		return new ResponseEntity<User>(this.service.getUser(userName),HttpStatus.OK);
	}

	
	@ResponseBody
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllPersons(){
		return new ResponseEntity<List<User>>(this.service.getAllUsers(), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/authenticate", method=RequestMethod.POST)
	public ResponseEntity<User> authenticateUser(@RequestBody User u){
		User user = this.service.getUser(u.getUserName());
		
		if (user!=null){
			if (user.getPassword().equals(u.getPassword())){
				return new ResponseEntity<User>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
}
