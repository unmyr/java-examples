package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
	private GreetingService greetingEnMale;
	private GreetingService greetingEnFemale;
	private GreetingService greetingEs;

	@Autowired @Qualifier("GreetingFrench")
	private GreetingService greetingFr;

	@Autowired
	public MainApp(
		GreetingService greetingPrimary,
		GreetingEnConfigClass configFemale,
		@Qualifier("GreetingSpanish") GreetingService esImpl
	) {
		this.greetingEnMale = greetingPrimary;
		this.greetingEnFemale = configFemale.getConfig();
		this.greetingEs = esImpl;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MainApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.greetingEnMale.sayHello());
		System.out.println(this.greetingEnFemale.sayHello());
		System.out.println(this.greetingEs.sayHello());
		System.out.println(this.greetingFr.sayHello());
	}

}
