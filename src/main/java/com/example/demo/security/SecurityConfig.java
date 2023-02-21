package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf().disable().addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class).
			authorizeHttpRequests().
			requestMatchers("api/categories/{categoryId}/product", "api/products/{productId}",
					"api/categories/{categoryId}/products", "api/categories", "api/categories/{categoryId}").hasAuthority("ROLE_ADMIN").
			requestMatchers("/register", "").hasAuthority("ROLE_USER").
//			requestMatchers(/**rutitas nuevas**/).hasAuthority("ROLE_USER").
			requestMatchers("/login").permitAll();
			return http.build();
		}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
}
