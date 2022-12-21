package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	@GetMapping("/hello/{name}")
	public ResponseEntity<?> greeting(@PathVariable(value = "name") String name) {
		Map<String,Object> map = new HashMap<>();
		map.put("message", String.format(template, name));
		return ResponseEntity.ok(map);
	}
}