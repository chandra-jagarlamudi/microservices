/**
 * 
 */
package com.ragas.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Chandra Jagarlamudi
 *
 */
@EnableWebSecurity
@Configuration
public class RatingServiceSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Right now the security semantics are very simple: 
	 * 1. Anyone can read resources
	 * 2. Only admins can modify resources
	 * 
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.disable()
		.authorizeRequests()
		.regexMatchers("^/ratings\\?bookId.*$")
		.authenticated()
		.antMatchers(HttpMethod.POST, "/ratings").authenticated()
		.antMatchers(HttpMethod.PATCH, "/ratings/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/ratings/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/ratings").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.csrf().disable();
	}

}
