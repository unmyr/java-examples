package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@ExtendWith(OutputCaptureExtension.class)
class DemoApplicationTests {
	@Test
	public void testRun(CapturedOutput output) throws Exception {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
			DemoApplicationTests.class
		)) {
			ctx.addApplicationListener(new ContextObserver());

			DemoApplication app = new DemoApplication();
			ctx.start();
			app.run(new String[] {"John"});
			ctx.stop();
			ctx.close();

			Assertions.assertThat(output).contains("ContextStartedEvent");
			Assertions.assertThat(output).contains("Hello John");
			Assertions.assertThat(output).contains("context is closed.");
		}
	}

}
