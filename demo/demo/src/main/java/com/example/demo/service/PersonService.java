package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

@Service
public class PersonService {
	
	private PersonDao personDao;
	
	@Autowired
	public PersonService(@Qualifier("postgres") PersonDao personDao) {
	//public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public UUID addPerson(Person person) {
		return personDao.addPerson(person);
	}
	
	public List<Person> getAllPeople() {
		return personDao.getPeople();
	}
	
	public Optional<Person> getPersonById(UUID personId) {
		return personDao.getPerson(personId);
	}
	
	public int deletePerson(UUID personId) {
		return personDao.deletePerson(personId);
	}
	
	public int updatePerson(UUID personId, Person person) {
	    return personDao.updatePerson(personId, person);
	}
}
