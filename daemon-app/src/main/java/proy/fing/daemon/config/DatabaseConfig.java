package proy.fing.daemon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
	
	@Value("${sqlLite.url}")
	private String url;
	
	@Bean
	DataSource getDataSource(){
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl(this.url);
		dataSource.setUsername("");
		dataSource.setPassword("");
		return dataSource;
	}
	
	@Bean
	JdbcTemplate getJdbcTemplate(){
		return new JdbcTemplate(getDataSource());
	}

}
