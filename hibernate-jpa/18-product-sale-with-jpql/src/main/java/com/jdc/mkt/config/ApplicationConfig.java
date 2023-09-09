package com.jdc.mkt.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@PropertySource("classpath:/connection.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jdc.mkt.repo")
public class ApplicationConfig {

	@Value("${db.url}") String url;
	@Value("${db.user}") String user;
	@Value("${db.password}") String password;
	@Bean
	DataSource dataSource() {
		var ds = new BoneCPDataSource();
		ds.setJdbcUrl(url);
		ds.setUser(user);
		ds.setPassword(password);
		return ds;
	}
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) throws IOException {
		var emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(ds);
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan("com.jdc.mkt.entity");
		emf.setJpaProperties(properties());
		return emf;
	}
	private Properties properties() throws IOException {
		var prop = new Properties();
		prop.load(getClass().getResourceAsStream("/jpa.properties"));
		return prop;
	}
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
