//package com.lcwd.user.service.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		FormLoginConfigurer<HttpSecurity> formLogin = httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/v1/api/users").permitAll().anyRequest().authenticated().and().formLogin();
//
//		return httpSecurity.build();
//	}
//}
