package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Person;

public interface PersonDao {

	public void save(Person person);

	public List<Person> findAll();

}
