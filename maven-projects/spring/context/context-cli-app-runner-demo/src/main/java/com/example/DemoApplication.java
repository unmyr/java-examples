package com.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.addListeners(new ContextObserver());
		app.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.getSourceArgs().length == 0) {
			System.out.println("Hello world");
		} else {
			System.out.println("Hello " + args.getNonOptionArgs().get(0));
		}
	}
}
