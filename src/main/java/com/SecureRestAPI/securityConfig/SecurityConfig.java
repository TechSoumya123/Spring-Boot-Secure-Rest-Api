package com.SecureRestAPI.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String[] SECURED_URLs = { "/api/v1/books/**" };
	private static final String[] UN_SECURED_URLs = { "/api/v1/books/getAll", "/api/v1/books/book/{id}", "/api/v1/users/**" };

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(CsrfConfigurer::disable)
				.authorizeHttpRequests(request -> {
														request.requestMatchers(UN_SECURED_URLs).permitAll()
														.requestMatchers(SECURED_URLs).hasAuthority("ADMIN").anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults()).build();
	}
}











