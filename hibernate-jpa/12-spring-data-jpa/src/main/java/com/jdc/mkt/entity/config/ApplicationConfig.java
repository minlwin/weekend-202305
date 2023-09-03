package com.jdc.mkt.entity.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class ApplicationConfig {

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	

	@Bean
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource datasource) {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(datasource);
		bean.setPackagesToScan("com.jdc.mkt.entity");
		bean.setJpaPropertyMap(jpaProperties());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return bean;
	}

	@Bean
	JpaTransactionManager transactionManager (EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	private Map<String, Object> jpaProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto", "create");
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.format_sql","true");
		return null;
	}
	
	
	
}
