package com.example;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Configuration
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext  (
			DemoApplication.class
		);
		context.addApplicationListener(new ContextObserver());
		context.start();
		System.out.println("Hello world");
		context.stop();
		context.close();
	}
}
