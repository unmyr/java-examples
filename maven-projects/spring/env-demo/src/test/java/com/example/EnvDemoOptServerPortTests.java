package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.core.env.Environment;

@SpringBootTest(args = "--demo.SERVER_PORT=8080")
@ExtendWith(OutputCaptureExtension.class)
public class EnvDemoOptServerPortTests {
  @Autowired Environment env;

  @Test
	public void testRun(CapturedOutput output) {
    Assertions.assertAll(
      () -> Assertions.assertEquals("8080", env.getProperty("demo.SERVER_PORT")),
      () -> Assertions.assertTrue(
        output.toString().contains("config:demo.SERVER_PORT=8080")
      )
    );
	}
}
