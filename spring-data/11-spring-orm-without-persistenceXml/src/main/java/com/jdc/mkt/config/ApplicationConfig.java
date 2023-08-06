package com.jdc.mkt.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.jdc.mkt.repo")
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean
	DataSource dataSource() {
		var dataSource = new EmbeddedDatabaseBuilder();
		dataSource.setType(EmbeddedDatabaseType.HSQL);
		return dataSource.build();
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		var emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("com.jdc.mkt.entity");
		emf.setJpaPropertyMap(jpaProperties());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return emf;
	}

	private Map<String, Object> jpaProperties() {
		Map<String, Object> map = new HashMap<>();
		map.put("hibernate.hbm2ddl.auto", "create");
		map.put("hibernate.show_sql", true);
		map.put("hibernate.format_sql", true);
		return map;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
