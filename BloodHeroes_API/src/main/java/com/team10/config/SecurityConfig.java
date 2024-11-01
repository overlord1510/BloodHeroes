package com.team10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthFilter authFilter;

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
						.requestMatchers("/api/auth/**","/api/refresh").permitAll()
						.requestMatchers("/api/donour/register","/api/hospital/register").permitAll()
						.anyRequest().authenticated()
					)
				.sessionManagement(mgmt->
					mgmt
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(excpt->
					excpt
						.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
					);
				
		//@formatter:on
		return http.build();
	}

}
