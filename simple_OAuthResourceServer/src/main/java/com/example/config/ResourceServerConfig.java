package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	String[] ignoredPaths = new String[] { "/error", "/login", "/doLogut", "/home", "/pageNotFound", "/css/**",
			"/js/**", "/fonts/**", "/img/**" };

	@Value("${security.oauth2.resource.user-info-uri}")
	private String userInfoUri;

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(ignoredPaths).permitAll().anyRequest().authenticated();
	}

	@Primary
	@Bean
	public UserInfoTokenServices tokenService() {
		final UserInfoTokenServices tokenService = new UserInfoTokenServices(userInfoUri, clientId);
		return tokenService;
	}
}