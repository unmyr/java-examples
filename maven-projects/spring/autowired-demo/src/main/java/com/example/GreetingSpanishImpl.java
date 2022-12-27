package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="GreetingSpanish")
public class GreetingSpanishImpl implements GreetingService {
  @Value("${demo.es.name:nil}")
  String name;

  @Override
  public String sayHello() {
    return "Hola " + name + ",";
  }
  
}
