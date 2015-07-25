package proy.fing.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import proy.fing.web.model.Person;

@Service
public class PersonBean implements PersonService {

	private Map<String, Person> persons = new HashMap<String, Person>();

	@Override
	public void addPerson(Person p) {
		this.persons.put(p.getFirstName(), p);
	}

	@Override
	public Person getPerson(String name) {
		return this.persons.get(name);
	}

	@Override
	public void deletePerson(String name) {
		this.persons.remove(name);
	}

	@Override
	public List<Person> getAllPersons() {
		return new ArrayList<Person>(this.persons.values());
	}

	@PostConstruct
	public void generatePersons() {
		// llenamos algunas personas de ejemplo

		Person p1 = new Person();
		p1.setAge(25);
		p1.setCi("4.547.166-8");
		p1.setCity("Montevideo");
		p1.setFirstName("Andres");
		p1.setLastName("Vera");

		Person p2 = new Person();
		p2.setAge(23);
		p2.setCi("4.321.250-1");
		p2.setCity("Maldonado");
		p2.setFirstName("Maite");
		p2.setLastName("Ibarburu");

		this.persons.put(p1.getFirstName(), p1);
		this.persons.put(p2.getFirstName(), p2);

	}
}
