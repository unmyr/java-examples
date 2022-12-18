package com.example;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class EnvDemoApplication implements CommandLineRunner {
	@Autowired
	private Environment env;

	@Autowired
	private EnvConfig config;

	@Value("${BOOL_VALUE:false}")
	private boolean boolValue;

	@Value("${LANG}")
	private String lang;

	@Value("${REMOTE_IP:0.0.0.0}")
	private InetAddress remoteIp;

	@Value("${SERVER_PORT:-1}")
	private int serverPort;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EnvDemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("getProperty:lang=" + env.getProperty("lang"));
		System.out.println("getProperty:BOOL_VALUE=" + env.getProperty("BOOL_VALUE")); // true/false/null
		System.out.println("getProperty:demo.langName=" + env.getProperty("demo.langName"));
		System.out.println("getProperty:demo.SERVER_PORT=" + env.getProperty("demo.SERVER_PORT"));
		System.out.println("Value:BOOL_VALUE=" + boolValue);
		System.out.println("Value:LANG=" + lang);
		System.out.println("Value:REMOTE_IP=" + remoteIp.getHostAddress());
		System.out.println("Value:SERVER_PORT=" + serverPort);
		System.out.println("config:demo.bool-value=" + config.isBoolValue()); // true/false
		System.out.println("config:demo.langName=" + config.getLangName());
		System.out.println("config:demo.remote-ip=" + config.getRemoteIp().getHostAddress());
		System.out.println("config:demo.SERVER_PORT=" + config.getServerPort());
	}
}
