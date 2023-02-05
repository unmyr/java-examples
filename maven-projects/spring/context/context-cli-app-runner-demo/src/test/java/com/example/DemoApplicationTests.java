package com.example;

import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Import;

@SpringBootTest(args = "John")
@ExtendWith(OutputCaptureExtension.class)
@Import(ContextObserver.class)
class DemoApplicationTests {

	@Test
	public void testRunEachLine(CapturedOutput output) throws Exception {
		Assertions.assertThat(output).contains(": context is refreshed.");
		Assertions.assertThat(output).contains("Hello John");
		Test.class.getClassLoader();
	}

	@Test
	public void testMultiline(CapturedOutput output) throws Exception {
		final InputStream is = ClassLoader.getSystemResourceAsStream("data_expected.txt");
		final byte[] b = new byte[is.available()];
		is.read(b);
		Assertions.assertThat(output).contains(new String(b, "UTF-8"));
	}

}
