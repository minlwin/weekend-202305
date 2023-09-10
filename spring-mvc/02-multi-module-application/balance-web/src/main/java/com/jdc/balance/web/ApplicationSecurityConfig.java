package com.jdc.balance.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.jdc.balance.web.security.ApplicationAuthSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.jdc.balance.web.security")
public class ApplicationSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers(
					new MvcRequestMatcher(introspector, "/"), 
					new MvcRequestMatcher(introspector, "/signin"), 
					new MvcRequestMatcher(introspector, "/signup"), 
					new MvcRequestMatcher(introspector, "/styles/**"), 
					new MvcRequestMatcher(introspector, "/js/**")
			).permitAll();
			request.requestMatchers(new MvcRequestMatcher(introspector, "/admin/**")).hasAuthority("Admin");
			request.requestMatchers(new MvcRequestMatcher(introspector, "/member/**")).hasAuthority("Member");
		});
		
		http.formLogin(form -> {
			form.loginPage("/signin");
			form.successHandler(new ApplicationAuthSuccessHandler());
		});
		
		http.logout(logout -> {
			logout.logoutUrl("/signout");
			logout.logoutSuccessUrl("/");
		});
		
		return http.build();
	}
}
