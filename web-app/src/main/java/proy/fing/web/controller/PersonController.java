package proy.fing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import proy.fing.web.model.Person;
import proy.fing.web.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@ResponseBody
	@RequestMapping(value = "/{name}", method=RequestMethod.GET)
	public Person getPerson(@PathVariable(value="name") String name){
		return this.service.getPerson(name);
	}

	
	@ResponseBody
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public List<Person> getAllPersons(){
		return this.service.getAllPersons();
	}
}
