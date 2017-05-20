package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.PersonDao;
import ar.edu.unlam.diit.scaw.entities.Person;

public class PersonDaoImpl implements PersonDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public PersonDaoImpl() {
		super();
	}

	@Override
	public void save(Person person) {

		String sql = "INSERT INTO PERSON (FIRSTNAME, LASTNAME, EMAIL) VALUES (:firstName, :lastName, :email)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstName", person.getFirstName());
		params.put("lastName", person.getLastName());
		params.put("email", person.getEmail());
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Person> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM PERSON";

		List<Person> result = jdbcTemplate.query(sql, params, new PersonMapper());

		return result;
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final class PersonMapper implements RowMapper<Person> {

		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setFirstName(rs.getString("firstName"));
			person.setLastName(rs.getString("lastName"));
			person.setEmail(rs.getString("email"));
			return person;
		}
	}

}
