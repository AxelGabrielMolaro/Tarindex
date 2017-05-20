package ar.edu.unlam.diit.scaw.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//@Configuration
public class HsqlDataSource {

	@Bean
	public DataSource dataSource() {

		System.out.println("create DDBB !!!!!!!!!");

		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("db/sql/create-db.sql")
				.addScript("db/sql/insert-data.sql").build();
		return db;
		
	}

}