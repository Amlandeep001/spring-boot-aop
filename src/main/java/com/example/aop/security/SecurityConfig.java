package com.example.aop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	 @Bean
	 InMemoryUserDetailsManager userDetailsService() {
		 
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
	        
	        return new InMemoryUserDetailsManager(user,  admin);
	    }

    // Secure the endpoins with HTTP Basic authentication
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/add/employee**").hasRole("USER")
                .antMatchers("/remove/employee**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().permitAll();
        
		return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/

}


