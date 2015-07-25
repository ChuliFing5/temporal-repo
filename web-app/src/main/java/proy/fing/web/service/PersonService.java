package proy.fing.web.service;

import java.util.List;

import proy.fing.web.model.Person;

public interface PersonService {
	
	public void addPerson(Person p);
	public Person getPerson(String name);
	public void deletePerson(String name);
	public List<Person> getAllPersons();

}
