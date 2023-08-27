package com.jdc.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		
		var builder = new MvcRequestMatcher.Builder(introspector);
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers(builder.pattern("/")).permitAll();
			req.requestMatchers(builder.pattern("/admin/**")).hasAuthority("Admin");
			req.requestMatchers(builder.pattern("/member/**")).hasAnyAuthority("Member", "Admin");
		});
		
		http.formLogin(form -> {});
		
		return http.build();
	}
}
