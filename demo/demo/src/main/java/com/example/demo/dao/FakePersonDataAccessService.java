package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

	private final static List<Person> DB = new ArrayList<>();
	
	@Override
	public UUID addPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getName()));
		return id;
	}

	@Override
	public List<Person> getPeople() {
		return DB;
	}

	@Override
	public int deletePerson(UUID id) {		
		Optional<Person> personOptional = getPerson(id);
		if (!personOptional.isPresent()) return 0;
		DB.remove(personOptional.get());
		return 1;
	}

	@Override
	public int updatePerson(UUID id, Person newPerson) {
		return getPerson(id).map(person -> {
			int indexOfPersonToUpate = DB.indexOf(person);
			if (indexOfPersonToUpate >= 0) {
				DB.set(indexOfPersonToUpate, new Person(id, newPerson.getName()));
				return 1;
			}
			return 0;
		}).orElse(0);		
	}

	@Override
	public Optional<Person> getPerson(UUID id) {
		return DB.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}
}
