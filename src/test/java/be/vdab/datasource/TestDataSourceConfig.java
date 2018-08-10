package be.vdab.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class TestDataSourceConfig {
	@Bean
	DataSource dataSource() {
		return new DriverManagerDataSource(
				"jdbc:mysql://localhost/groenetenen?useSSL=false", "cursist", "cursist");
	}
}
