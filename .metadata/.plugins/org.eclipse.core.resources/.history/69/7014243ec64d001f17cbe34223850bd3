package com.team10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//@formatter:off
		http
				.csrf(csrf->
					csrf
						.disable()
					)
				.authorizeHttpRequests(authorize->
					authorize
						.requestMatchers("/api/auth/**","/api/donour/register","/api/organization/register").permitAll()
						.anyRequest().authenticated()
					)
				.sessionManagement(mgmt->
					mgmt
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
				.authenticationProvider(authenticationProvider);
		//@formatter:on
		return http.build();
	}

}
