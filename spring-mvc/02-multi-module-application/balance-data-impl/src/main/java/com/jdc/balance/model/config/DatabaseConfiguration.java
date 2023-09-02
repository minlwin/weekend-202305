package com.jdc.balance.model.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jdc.balance.model.service.impl")
@PropertySource("classpath:/database.properties")
public class DatabaseConfiguration {

	@Value("${app.database.url}")
	private String url;
	@Value("${app.database.username}")
	private String username;
	@Value("${app.database.password}")
	private String password;

	@Bean
	DataSource dataSource() {
		var bean = new BasicDataSource();
		bean.setUrl(url);
		bean.setUsername(username);
		bean.setPassword(password);
		bean.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return bean;
	}
	
	@Bean
	TransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
