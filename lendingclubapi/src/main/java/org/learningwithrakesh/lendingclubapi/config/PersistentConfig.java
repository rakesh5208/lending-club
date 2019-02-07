package org.learningwithrakesh.lendingclubapi.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 *
 */
@Configuration
@EnableAutoConfiguration
@PropertySource(value = { "classpath:application.properties" })
@EntityScan(basePackages = { "org.learningwithrakesh.lendingclubapi" })
public class PersistentConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {
		System.out.println("Initiating datasource");
		DataSourceBuilder<?> create = DataSourceBuilder.create();
		create.url(resloveProperty("app.datasource.url"));
		create.username(resloveProperty("app.datasource.username"));
		create.password(resloveProperty("app.datasource.password"));
		create.driverClassName(resloveProperty("app.datasource.driver-class-name"));
		return create.build();
	}

	@Bean
	public LocalSessionFactoryBean setupSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("org.learningwithrakesh.lendingclubapi.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", resloveProperty("app.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.ddl-auto", resloveProperty("app.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.show_sql", resloveProperty("app.jpa.show-sql"));
		properties.setProperty("hibernate.hbm2ddl.auto", resloveProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	private String resloveProperty(String key) {
		return env.getRequiredProperty(key);
	}
}
