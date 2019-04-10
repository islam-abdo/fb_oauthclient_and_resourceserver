package com.example.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	OAuth2RestTemplate restTemplate;

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@RequestMapping("/hi")
	public String hi(Principal principal) {
		return "Hi, " + principal.getName();
	}

	@RequestMapping("/hi2")
	public String hi2(Principal principal) {
		final String greeting = restTemplate.getForObject("http://127.0.0.1:8082/api/hello", String.class);

		System.out.println(greeting);
		return greeting;
	}
}
