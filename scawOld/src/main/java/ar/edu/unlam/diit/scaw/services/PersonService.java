package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Person;

public interface PersonService {

	public void save(Person person);

	public List<Person> findAll();

}
