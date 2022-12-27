package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("GreetingFrench")
public class GreetingFrenchImpl implements GreetingService {
  @Value("${demo.fr.name:nil}")
  String name;

  @Override
  public String sayHello() {
    return "Bonjour " + name + ".";
  }
  
}
