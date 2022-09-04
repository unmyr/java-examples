package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	@GetMapping("/hello/{name}")
	public Greeting greeting(@PathVariable(value = "name") String name) {
		return new Greeting(String.format(template, name));
	}
}