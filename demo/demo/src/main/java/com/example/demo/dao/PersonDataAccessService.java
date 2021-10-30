package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public UUID addPerson(UUID id, Person person) {
		String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
		jdbcTemplate.update(sql, id, person.getName());
		return id;
	}

	@Override
	public List<Person> getPeople() {		
		//return List.of(new Person(UUID.randomUUID(), "FROM POSTGRES DB"));
		String sql = "SELECT id, name FROM person";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			return new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"));
		});
	}

	@Override
	public Optional<Person> getPerson(UUID id) {		
		//return Optional.empty();
		String sql = "SELECT id, name FROM person WHERE id = ?";
		Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
			UUID personId = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(personId, name);
		});
		return Optional.ofNullable(person);
	}

	@Override
	public int deletePerson(UUID id) {
		String sql = "DELETE FROM person WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updatePerson(UUID id, Person person) {
		String sql = "UPDATE person SET name = ? WHERE id = ?";
		return jdbcTemplate.update(sql, person.getName(), id);
	}

}
