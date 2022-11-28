package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OneServiceTests {
	@Autowired
	private OneService service;

	@Test
	void testTestConnection() {
		int result = service.testConnection();
		Assertions.assertThat(
			result
		).isEqualTo(1);
	}
}
