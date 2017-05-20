package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.PersonDao;
import ar.edu.unlam.diit.scaw.entities.Person;
import ar.edu.unlam.diit.scaw.services.PersonService;

public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDao personDao;
	
	@Override
	public void save(Person person) {
		personDao.save(person);
	}

	@Override
	public List<Person> findAll() {
		return personDao.findAll();
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	

}
