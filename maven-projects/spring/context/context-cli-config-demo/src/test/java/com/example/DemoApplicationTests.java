package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
class DemoApplicationTests {
	@Test
	void contextLoads() {
	}

	@Test
	public void testRun(CapturedOutput output) throws Exception {
		DemoApplication app = new DemoApplication();
		app.run(new String[] {"John"});
		Assertions.assertTrue(
			output.toString().contains("Hello John"), 
			"output does not contain John"
		);
	}

}
