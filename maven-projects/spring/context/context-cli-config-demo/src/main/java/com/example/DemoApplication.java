package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args)) {
			ctx.addApplicationListener(new ContextObserver());
			ctx.start();
			DemoApplication app = ctx.getBean(DemoApplication.class);
			app.run(args);
			ctx.stop();
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Hello world");
		} else {
			System.out.println("Hello " + args[0]);
		}
	}
}
