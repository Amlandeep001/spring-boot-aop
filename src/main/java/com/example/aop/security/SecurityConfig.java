package com.example.aop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Bean
	InMemoryUserDetailsManager userDetailsService()
	{
		UserDetails user = User
				.withUsername("user")
				.password("{noop}password")
				.roles("USER")
				.build();

		UserDetails admin = User
				.withUsername("admin")
				.password("{noop}password")
				.roles("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	// Secure the end-points with HTTP Basic authentication
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http
				// HTTP Basic authentication
				.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/add/employee**").hasRole("USER")
						.requestMatchers("/remove/employee**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.csrf((csrf) -> csrf.disable())
				.formLogin(formLogin -> formLogin
						.permitAll());

		return http.build();
	}
}
